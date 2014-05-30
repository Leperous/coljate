package net.ollie.sc4j;

import java.util.Comparator;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Indexed;
import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.IndexedComparator;

/**
 * A sorted, iterable and indexed collection of objects.
 *
 * @author Ollie
 */
public interface Array<V>
        extends List<V>, Indexed<V> {

    @Override
    default Comparator<? super V> comparator() {
        return new IndexedComparator<>(this);
    }

    @Override
    Array<V> segment(int from, int to);

    @Override
    Array<V> tail();

    @Override
    Array<V> filter(Predicate<? super V> predicate);

    @Override
    Array<V> filterKeys(Predicate<? super Integer> predicate);

    @Override
    <V2> Array<V2> map(Function<? super V, ? extends V2> function);

    @Override
    default Array<V> toArray() {
        return this;
    }

    @Override
    Array.Mutable<V> mutable();

    @Override
    Array.Immutable<V> immutable();

    @Override
    default Array<V> values() {
        return this;
    }

    default boolean equals(final Array<?> that) {
        return List.super.equals(that);
    }

    /**
     *
     * @param <V>
     */
    interface Mutable<V>
            extends Array<V>, List.Mutable<V>, Indexed.Mutable<V> {

        void resize(int size);

    }

    /**
     *
     * @param <V>
     */
    interface Immutable<V>
            extends Array<V>, List.Immutable<V>, Indexed.Immutable<V> {

        @Override
        Array.Immutable<V> sort(Comparator<? super V> comparator);

        @Override
        Array.Immutable<V> segment(int from, int to);

        default Array.Immutable<V> resize(int size) {
            return this.segment(0, size);
        }

        @Override
        default Array.Immutable<V> toArray() {
            return this;
        }

        @Override
        default Array.Immutable<V> immutable() {
            return this;
        }

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

    /**
     *
     * @param <V>
     */
    interface Empty<V>
            extends List.Empty<V>, Array.Immutable<V> {

        @Override
        default boolean contains(final Object object) {
            return List.Empty.super.contains(object);
        }

        @Override
        default Object[] toRawArray() {
            return Arrays.empty();
        }

        @Override
        default V get(final int index) {
            throw new IndexOutOfBoundsException();
        }

        @Override
        default Array.Empty<V> segment(int from, int to) throws IndexOutOfBoundsException {
            throw new IndexOutOfBoundsException();
        }

        @Override
        default OptionalInt indexOf(final Object value) {
            return OptionalInt.empty();
        }

        @Override
        default Array.Empty<V> values() {
            return this;
        }

        @Override
        default Array.Empty<V> tail() {
            return this;
        }

        @Override
        default Array.Empty<V> immutable() {
            return this;
        }

        @Override
        default Array.Empty<V> sort(final Comparator<? super V> comparator) {
            return this;
        }

        @Override
        default Array.Empty<V> withoutFirst(Object value) {
            return this;
        }

        @Override
        default Array.Empty<V> withoutAll(Object value) {
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Array.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Array.Empty<V2>) this;
        }

        @Override
        default Array.Empty<V> filter(Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default V last() {
            return List.Empty.super.last();
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
                    : this.get(this.size() - 1);
        }

        @Override
        default V lastOrElse(final Predicate<? super V> predicate, final V defaultValue) {
            for (int i = this.size() - 1; i >= 0; i--) {
                final V element = this.get(i);
                if (predicate.test(element)) {
                    return element;
                }
            }
            return defaultValue;
        }

    }

}
