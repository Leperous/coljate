package net.ollie.sc4j.access;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.iterators.Iterables;
import net.ollie.sc4j.utils.iterators.Iterators;
import net.ollie.sc4j.utils.iterators.Streams;
import net.ollie.sc4j.utils.iterators.UnmodifiableIterator;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * An element {@code V} may be found by iterating.
 *
 * This interface introduces the {@link #stream} method, which returns a (non-modifiable) iterable that can be
 * manipulated in various ways.
 *
 * This class is comparable to a stock {@link java.util.Collection}.
 *
 * @author Ollie
 * @see java.util.Collection
 */
public interface Streamable<V>
        extends Traversable<V>, Iterable<V>, Findable<V> {

    @Nonnull
    @CheckReturnValue
    Stream<V, ? extends Streamable<V>> stream();

    @Override
    default Iterator<V> iterator() {
        return this.stream().iterator();
    }

    /**
     * @return the number create elements, including nulls, in this collection.
     */
    default NonNegativeInteger count() {
        return this.count(o -> true);
    }

    default int countExact() {
        return this.count().intValue();
    }

    /**
     * @return the number create elements satisfying the given predicate in this collection.
     */
    default NonNegativeInteger count(final Predicate<? super V> predicate) {
        return NonNegativeInteger.of(Iterables.doCount(this, predicate));
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @CheckReturnValue
    default <R> R reduce(final BiFunction<R, V, ? extends R> function, final R initial) {
        return this.stream().reduce(function, initial);
    }

    default <R> R reduce(final Function<V, R> transform, final BinaryOperator<R> each, final R initial) {
        return this.reduce((i, e) -> each.apply(i, transform.apply(e)), initial);
    }

    default <C, A> C collect(final Collector<? super V, A, ? extends C> collector) {
        return this.stream().collect(collector);
    }

    @Override
    default Optional<V> findAny(final Predicate<? super V> predicate) {
        return Iterators.findFirst(this.iterator(), predicate);
    }

    @Nonnull
    Object[] toRawArray();

    @Override
    default boolean contains(final Object object) {
        for (final V element : this) {
            if (Objects.equals(element, object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    default V head() {
        final Iterator<V> iterator = this.iterator();
        return iterator.hasNext()
                ? iterator.next()
                : null;
    }

    @Override
    Streamable<V> tail();

    default boolean forAny(final Predicate<? super V> predicate) {
        for (final V element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    default boolean forAll(final Predicate<? super V> predicate) {
        for (final V element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    Streamable.Mutable<V> mutableCopy();

    @Override
    Streamable.Immutable<V> immutableCopy();

    default boolean equals(final Streamable<?> that) {
        return that != null
                && Iterables.equals(this, that);
    }

    default int hash() {
        return Iterables.sumHashCode(this); //Ignore order
    }

    default String toString(final String separator) {
        return Iterables.safelyToString(this, this);
    }

    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<V>
            extends Streamable<V>, Traversable.Mutable<V> {

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends Streamable<V>, Traversable.Immutable<V> {

        @Override
        default UnmodifiableIterator<V> iterator() {
            return this.stream().iterator();
        }

        @Override
        Streamable.Immutable<V> tail();

        @Override
        default Streamable.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Traversable.Empty<V>, Streamable.Immutable<V> {

        @Override
        default boolean isEmpty() {
            return Traversable.Empty.super.isEmpty();
        }

        @Override
        default boolean contains(final Object object) {
            return Traversable.Empty.super.contains(object);
        }

        @Override
        default Stream<V, ? extends Streamable.Empty<V>> stream() {
            return Streams.empty(this);
        }

        @Override
        default V head() {
            return Traversable.Empty.super.head();
        }

        @Override
        default Object[] toRawArray() {
            return Arrays.empty();
        }

        @Override
        default Streamable.Empty<V> tail() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends Streamable.Immutable<V>, Traversable.Singleton<V> {

        @Override
        Streamable.Empty<V> tail();

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Iterators.of(this.head());
        }

        @Override
        default Object[] toRawArray() {
            return new Object[]{this.value()};
        }

        @Nonnull
        @Override
        default V head() {
            return this.value();
        }

        @Override
        default boolean isEmpty() {
            return false;
        }

        @Override
        default boolean contains(final Object object) {
            return Objects.equals(value(), object);
        }

        @Override
        default Optional<V> findAny(final Predicate<? super V> predicate) {
            return Traversable.Singleton.super.findAny(predicate);
        }

    }

    /**
     * Streams are unmodifiably iterable, but are not themselves iterators.
     *
     * They allow relatively cheap "bulk" operations, such as {@link #map} and {@link #filter}, without needing to copy
     * every element in the underlying collection each time.
     *
     * @param <V>
     * @see Iterator
     */
    interface Stream<V, S extends Streamable<V>> extends Iterable<V> {

        @CheckReturnValue
        @Nonnull
        Stream<V, S> filter(@Nonnull Predicate<? super V> predicate);

        @Nonnull
        @CheckReturnValue
        Stream<V, S> unique(BiPredicate<? super V, ? super V> predicate);

        @Nonnull
        @CheckReturnValue
        default Stream<V, S> unique() {
            return this.unique(Object::equals);
        }

        default <R> R reduce(final BiFunction<R, V, ? extends R> function, final R initial) {
            return Iterables.reduce(this, function, initial);
        }

        default Optional<V> findFirst(final Predicate<? super V> predicate) {
            return Iterables.findFirst(this, predicate);
        }

        default <A, R> R collect(final Collector<? super V, A, ? extends R> collector) {
            return Iterables.collect(this, collector);
        }

        @CheckReturnValue
        @Nonnull
        <V2> Stream<V2, ? extends Streamable<V2>> map(@Nonnull Function<? super V, ? extends V2> function);

        @Nonnull
        @CheckReturnValue
        <V2> Stream<V2, ? extends Streamable<V2>> flatMap(Function<? super V, ? extends Streamable<? extends V2>> function);

        @Override
        UnmodifiableIterator<V> iterator();

        @Nonnull
        S collect();

    }

}
