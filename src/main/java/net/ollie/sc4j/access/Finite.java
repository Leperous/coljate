package net.ollie.sc4j.access;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterators;
import net.ollie.sc4j.utils.UnmodifiableIterator;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * An element {@code V} may be found by iterating.
 *
 * This interface introduces the {@link #reduce} method.
 *
 * It it comparable to a stock {@link java.util.Collection}.
 *
 * @author Ollie
 * @see java.util.Collection
 */
public interface Finite<V>
        extends Traversable<V>, Iterable<V>, Findable<V> {

    @Nonnull
    Stream<V, ? extends Finite<V>> stream();

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
        R current = initial;
        for (final V element : this) {
            current = function.apply(current, element);
        }
        return current;
    }

    default <R> R reduce(final Function<V, R> transform, final BinaryOperator<R> each, final R initial) {
        return reduce((i, e) -> each.apply(i, transform.apply(e)), initial);
    }

    default <C, A> C collect(final Collector<? super V, A, C> collector) {
        final A into = collector.supplier().get();
        final BiConsumer<A, ? super V> accumulator = collector.accumulator();
        for (final V element : this) {
            accumulator.accept(into, element);
        }
        return collector.finisher().apply(into);
    }

    @Override
    default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
        for (final V element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return defaultValue;
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
    Finite<V> tail();

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
    Finite.Mutable<V> mutableCopy();

    @Override
    Finite.Immutable<V> immutableCopy();

    default boolean equals(final Finite<?> that) {
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
            extends Finite<V>, Traversable.Mutable<V> {

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends Finite<V>, Traversable.Immutable<V> {

        @Override
        Finite.Immutable<V> tail();

        @Override
        UnmodifiableIterator<V> iterator();

        @Override
        default Finite.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends Traversable.Empty<V>, Finite.Immutable<V> {

        @Override
        default boolean isEmpty() {
            return Traversable.Empty.super.isEmpty();
        }

        @Override
        default boolean contains(final Object object) {
            return Traversable.Empty.super.contains(object);
        }

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Iterators.empty();
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
        default Finite.Empty<V> tail() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends Finite.Immutable<V>, Traversable.Singleton<V> {

        @Override
        Finite.Empty<V> tail();

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Iterators.singleton(this.value());
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
        default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            return predicate.test(value())
                    ? value()
                    : defaultValue;
        }

    }

    interface Stream<V, C extends Finite<V>>
            extends Iterable<V> {

        @CheckReturnValue
        @Nonnull
        Stream<V, C> filter(@Nonnull Predicate<? super V> predicate);

        @CheckReturnValue
        @Nonnull
        <V2> Stream<V2, ? extends Finite<V2>> map(@Nonnull Function<? super V, ? extends V2> function);

        default <R> R reduce(final BiFunction<R, V, ? extends R> function, R initial) {
            return this.collect().reduce(function, initial);
        }

        @Nonnull
        @CheckReturnValue
        <V2> Stream<V2, ? extends Finite<V2>> flatMap(Function<? super V, ? extends Finite<? extends V2>> function);

        default V findFirst(final Predicate<? super V> predicate) {
            return this.collect().findFirst(predicate);
        }

        C collect();

        default <A, R> R collect(final Collector<? super V, A, ? extends R> collector) {
            return Iterables.collect(this, collector);
        }

    }

}
