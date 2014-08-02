package net.ollie.sc4j;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.access.Indexed;
import net.ollie.sc4j.imposed.Sorted;
import net.ollie.sc4j.utils.Functions;
import net.ollie.sc4j.utils.IndexedComparator;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * An ordered, indexed collection of objects.
 *
 * @author Ollie
 */
public interface Array<V>
        extends List<V>, Indexed<V>, Sorted<V> {

    int capacity();

    @Override
    Array<V> segment(int from, int to);

    @Override
    Array<V> reverse();

    @Override
    Array<V> tail();

    @Override
    default Comparator<? super V> comparator() {
        return new IndexedComparator<>(this);
    }

    @Override
    default Array<V> filter(final Predicate<? super V> predicate) {
        return this.map(Functions.satisfying(predicate));
    }

    @Override
    default Array<V> filterValues(final Predicate<? super V> predicate) {
        return this.filter(predicate);
    }

    @Override
    <V2> Array<V2> map(Function<? super V, ? extends V2> function);

    @Override
    default <V2> Array<V2> mapValues(Function<? super V, ? extends V2> function) {
        return this.map(function);
    }

    @Override
    <R> Array<R> flatMap(Function<? super V, ? extends Finite<R>> function);

    @Override
    Array.Mutable<V> mutableCopy();

    @Override
    Array.Immutable<V> immutableCopy();

    @Override
    default Array<V> values() {
        return this;
    }

    default boolean equals(final Array<?> that) {
        return List.super.equals(that);
    }

    /**
     * @param <V>
     */
    interface Mutable<V>
            extends Array<V>, List.Mutable<V>, Indexed.Mutable<V> {

        void sort(Comparator<? super V> comparator);

        void setCapacity(int capacity);

        @Override
        Array.Mutable<V> reverse();

        @Override
        <V2> Array.Mutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        default V first() {
            return this.iterator().next();
        }

        @Override
        default void prefix(final V value) {
            this.insert(this.start(), value);
        }

        default int start() {
            return 0;
        }

    }

    /**
     * @param <V>
     */
    interface Immutable<V>
            extends Array<V>, List.Immutable<V>, Indexed.Immutable<V> {

        Array.Immutable<V> sort(Comparator<? super V> comparator);

        @Override
        Array.Immutable<V> segment(int from, int to);

        @Override
        Array.Immutable<V> reverse();

        default Array.Immutable<V> resize(int size) {
            return this.segment(0, size);
        }

        @Override
        default Array.Immutable<V> immutableCopy() {
            return this;
        }

        @Override
        <R> Array.Immutable<R> flatMap(Function<? super V, ? extends Finite<R>> function);

        @Override
        Array.Immutable<V> tail();

        @Override
        Array.Immutable<V> withPrefix(V value);

        @Override
        Array.Immutable<V> withSuffix(V value);

        @Override
        Array.Immutable<V> withoutFirst(Object value);

        @Override
        Array.Immutable<V> withoutAll(Object value);

        @Override
        <V2> Array.Immutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        Array.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        default Array.Immutable<V> values() {
            return this;
        }

    }

    interface NonThreadSafeArray<V>
            extends Array.Mutable<V> {

        @Override
        default V first() {
            return this.isEmpty()
                    ? null
                    : this.get(0);
        }

        @Override
        default V last() {
            return this.isEmpty()
                    ? null
                    : this.get(this.count() - 1);
        }

        default V lastOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            for (int i = this.count() - 1; i >= 0; i--) {
                final V element = this.get(i);
                if (predicate.test(element)) {
                    return element;
                }
            }
            return defaultValue;
        }

    }

    interface Empty<V>
            extends Array.Immutable<V>, List.Empty<V>, Indexed.Empty<V> {

        @Override
        default boolean contains(final Object object) {
            return List.Empty.super.contains(object);
        }

        @Override
        default boolean isEmpty() {
            return List.Empty.super.isEmpty();
        }

        @Override
        default <V2> Array.Empty<V2> map(Function<? super V, ? extends V2> function) {
            throw new UnsupportedOperationException("map not supported yet!");
        }

        @Override
        default Array.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default Array.Empty<V> tail() {
            return this;
        }

        @Override
        default int capacity() {
            return 0;
        }

        @Override
        default Array.Empty<V> immutableCopy() {
            return this;
        }

    }

    interface Singleton<V>
            extends Array.Immutable<V>, List.Singleton<V>, Indexed.Singleton<V> {

        @Override
        default boolean isEmpty() {
            return List.Singleton.super.isEmpty();
        }

        @Override
        default int capacity() {
            return 1;
        }

        @Override
        default boolean contains(final Object object) {
            return List.Singleton.super.contains(object);
        }

        @Override
        Array.Empty<V> tail();

        @Override
        default Array.Singleton<V> immutableCopy() {
            return this;
        }

    }

}
