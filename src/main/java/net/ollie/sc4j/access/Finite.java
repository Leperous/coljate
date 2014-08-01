package net.ollie.sc4j.access;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterators;
import net.ollie.sc4j.utils.UnmodifiableIterator;

import java.math.BigInteger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * Iterable, finite collection. Introduces "reduce" method.
 *
 * @author Ollie
 * @see java.util.Collection
 */
public interface Finite<V>
        extends Traversable<V>, Iterable<V> {

    default int count() {
        return this.count(o -> true);
    }

    default int count(final Predicate<? super V> predicate) {
        return Iterables.doCount(this, predicate);
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @Override
    <R> Finite<R> map(Function<? super V, ? extends R> function);

    @Nonnull
    @CheckReturnValue
    <R> Finite<R> flatMap(Function<? super V, ? extends Finite<R>> function);

    @CheckReturnValue
    default <R> R reduce(final BiFunction<R, V, ? extends R> function, final R initial) {
        R current = initial;
        for (final V element : this) {
            current = function.apply(current, element);
        }
        return current;
    }

    default BigInteger sum(final Function<V, BigInteger> function) {
        return reduce((i, e) -> i.add(function.apply(e)), BigInteger.ZERO);
    }

    default int sumExact(final Function<V, Integer> function) {
        return this.sum(v -> BigInteger.valueOf(function.apply(v))).intValueExact();
    }

    @Nonnull
    Object[] toRawArray();

    @Override
    Finite<V> filter(Predicate<? super V> predicate);

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

    @Override
    default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
        for (final V element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return defaultValue;
    }

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

    default <C, A> C collect(final Collector<? super V, A, C> collector) {
        final A into = collector.supplier().get();
        final BiConsumer<A, ? super V> accumulator = collector.accumulator();
        for (final V element : this) {
            accumulator.accept(into, element);
        }
        return collector.finisher().apply(into);
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

    String toString(String separator);

    interface Mutable<V>
            extends Finite<V>, Traversable.Mutable<V> {

    }

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

        @Override
        default <V2> Finite.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Finite.Empty<V2>) this;
        }

        @Override
        default Finite.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default V findOrElse(final Predicate<? super V> predicate, V defaultValue) {
            return Traversable.Empty.super.findOrElse(predicate, defaultValue);
        }

    }

    interface Singleton<V>
            extends Traversable.Singleton<V>, Finite.Immutable<V> {

        @Override
        default UnmodifiableIterator<V> iterator() {
            return Iterators.singleton(value());
        }

        @Override
        default Object[] toRawArray() {
            return new Object[]{value()};
        }

        @Override
        default V head() {
            return value();
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

}
