package net.ollie.sc4j;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.access.Indexed;
import net.ollie.sc4j.imposed.Ordered;
import net.ollie.sc4j.utils.Functions;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * An ordered, indexed collection create objects.
 *
 * @author Ollie
 */
public interface Array<V>
        extends List<V>, Indexed<NonNegativeInteger, V>, Ordered<V> {

    V get(int index) throws IndexOutOfBoundsException;

    @Override
    default V get(final Object key) throws IndexOutOfBoundsException {
        return key instanceof Number
                ? this.get(NonNegativeInteger.of((Number) key))
                : null;
    }

    @Override
    default V get(final NonNegativeInteger index) {
        return this.get(index.intValue());
    }

    default NonNegativeInteger start() {
        return NonNegativeInteger.ZERO;
    }

    NonNegativeInteger capacity();

    @Override
    default Array<V> segment(NonNegativeInteger from, NonNegativeInteger to) throws IndexOutOfBoundsException {
        return this.segment(from.intValue(), to.intValue());
    }

    @Nonnull
    @CheckReturnValue
    Array<V> segment(final int from, final int to) throws IndexOutOfBoundsException;

    @Override
    Array<V> reverse();

    @Override
    Array<V> tail();

    @Override
    default Array<V> filter(final Predicate<? super V> predicate) {
        return this.map(Functions.satisfying(predicate));
    }

    @Override
    Array<V> filterKeys(Predicate<? super NonNegativeInteger> predicate);

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
    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<V>
            extends Array<V>, List.Mutable<V>, Indexed.Mutable<NonNegativeInteger, V> {

        void sort(Comparator<? super V> comparator);

        void setCapacity(NonNegativeInteger capacity);

        @Override
        Array.Mutable<V> reverse();

        @Override
        <V2> Array.Mutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        default V first() {
            return this.isEmpty()
                    ? null
                    : this.get(this.start());
        }

        @Override
        default V last() {
            return this.isEmpty()
                    ? null
                    : this.get(this.count().decrement().get());
        }

        @Override
        default void prefix(final V value) {
            this.insert(this.start(), value);
        }

        V set(int index, V value) throws IndexOutOfBoundsException;

        @Override
        default V set(final NonNegativeInteger index, final V value) throws IndexOutOfBoundsException {
            return this.set(index.intValue(), value);
        }

        void insert(int index, V value) throws IndexOutOfBoundsException;

        @Override
        default void insert(final NonNegativeInteger index, V value) throws IndexOutOfBoundsException {
            this.insert(index.intValue(), value);
        }

        default V remove(final int index) throws IndexOutOfBoundsException {
            return this.set(index, null);
        }

        @Override
        default V remove(final NonNegativeInteger index) throws IndexOutOfBoundsException {
            return this.remove(index.intValue());
        }

    }

    @javax.annotation.concurrent.ThreadSafe
    interface Concurrent<V>
            extends Array.Mutable<V>, List.Concurrent<V> {

        @Override
        V first();

        @Override
        V last();

    }

    /**
     * @param <V>
     */
    @javax.annotation.concurrent.Immutable
    interface Immutable<V>
            extends Array<V>, List.Immutable<V>, Indexed.Immutable<NonNegativeInteger, V> {

        Array.Immutable<V> sort(Comparator<? super V> comparator);

        @Override
        SortedSet.Immutable<NonNegativeInteger> keys();

        @Override
        default Array.Immutable<V> segment(final NonNegativeInteger from, final NonNegativeInteger to) {
            return this.segment(from.intValue(), to.intValue());
        }

        @Override
        Array.Immutable<V> segment(int from, int to);

        @Override
        Array.Immutable<V> reverse();

        @Nonnull
        @CheckReturnValue
        default Array.Immutable<V> resize(final NonNegativeInteger size) {
            return this.resize(size.intValue());
        }

        @Nonnull
        @CheckReturnValue
        default Array.Immutable<V> resize(final int size) {
            return this.segment(this.start().intValue(), size);
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
        Array.Immutable<V> andPrefix(V value);

        @Override
        Array.Immutable<V> andSuffix(V value);

        @Override
        Array.Immutable<V> notFirst(Object value);

        @Override
        Array.Immutable<V> notAll(Object value);

        @Override
        <V2> Array.Immutable<V2> map(Function<? super V, ? extends V2> function);

        @Override
        Array.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        default Array.Immutable<V> values() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends Array.Immutable<V>, List.Empty<V>, Indexed.Empty<NonNegativeInteger, V> {

        @Override
        default V get(final int index) {
            throw new ArrayIndexOutOfBoundsException();
        }

        @Override
        SortedSet.Empty<NonNegativeInteger> keys();

        @Override
        default Array.Empty<V> sort(final Comparator<? super V> comparator) {
            return this;
        }

        @Override
        Array.Singleton<V> andPrefix(V value);

        @Override
        default Array.Empty<V> notFirst(final Object value) {
            return this;
        }

        @Override
        default Array.Empty<V> notAll(final Object value) {
            return this;
        }

        @Override
        default Array.Empty<V> reverse() {
            return this;
        }

        @Override
        default Array.Singleton<V> andSuffix(final V value) {
            return this.andPrefix(value);
        }

        @Override
        default Array.Empty<V> segment(final int from, final int to) {
            if (from == 0 && to == 0) {
                return this;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Array.Empty<V2> map(Function<? super V, ? extends V2> function) {
            return (Array.Empty<V2>) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Array.Empty<V2> flatMap(Function<? super V, ? extends Finite<V2>> function) {
            return (Array.Empty<V2>) this;
        }

        @Override
        default Array.Empty<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default Array.Empty<V> filterKeys(final Predicate<? super NonNegativeInteger> predicate) {
            return this;
        }

        @Override
        default Array.Empty<V> tail() {
            return this;
        }

        @Override
        default NonNegativeInteger capacity() {
            return NonNegativeInteger.ZERO;
        }

        @Override
        default Array.Empty<V> immutableCopy() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Singleton<V>
            extends Array.Immutable<V>, List.Singleton<V> {

        @Override
        default V get(final int i) {
            if (i == 0) {
                return this.value();
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        @Override
        default Array.Singleton<V> sort(final Comparator<? super V> comparator) {
            return this;
        }

        @Override
        default Array.Immutable<V> andSuffix(final V value) {
            return this.andPrefix(value).reverse();
        }

        @Override
        default Array.Immutable<V> notAll(Object value) {
            return this.notFirst(value);
        }

        @Override
        default boolean contains(final Object object) {
            return List.Singleton.super.contains(object);
        }

        @Override
        default boolean isEmpty() {
            return List.Singleton.super.isEmpty();
        }

        @Override
        Array.Empty<V> tail();

        @Override
        default Array.Singleton<V> reverse() {
            return this;
        }

        @Override
        <V2> Array.Singleton<V2> map(Function<? super V, ? extends V2> function);

        @Override
        default NonNegativeInteger capacity() {
            return NonNegativeInteger.ONE;
        }

    }

}
