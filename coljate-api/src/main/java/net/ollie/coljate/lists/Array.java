package net.ollie.coljate.lists;

import net.ollie.coljate.sets.SortedSet;

import java.util.Comparator;
import java.util.Objects;
import java.util.RandomAccess;

import net.ollie.coljate.access.Indexed;
import net.ollie.coljate.imposed.Ordered;
import net.ollie.coljate.utils.Exceptions;
import net.ollie.coljate.utils.iterators.Streams;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * An ordered, indexed collection.
 *
 * @author Ollie
 */
public interface Array<V>
        extends List<V>, Indexed<NonNegativeInteger, V>, Ordered<V>, RandomAccess {

    @Override
    Stream<V, ? extends Array<V>> stream();

    @CheckForNull
    V get(int index) throws IndexOutOfBoundsException;

    @Override
    default V get(final NonNegativeInteger index) {
        return this.get(index.intValue());
    }

    @Override
    default V first() {
        return this.head();
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
    Array<V> reverseCopy();

    @Override
    Array<V> tail();

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
        Array.Mutable<V> reverseCopy();

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

        @Override
        default Inliner<V, ? extends Array.Mutable<V>> inline() {
            return new Inliner<>(this);
        }

        class Inliner<V, A extends Array.Mutable<V>> extends List.Mutable.Inliner<V, A> {

            private final A array;

            protected Inliner(final A array) {
                super(array);
                this.array = array;
            }

            public Inliner<V, A> insert(final int index, final V value) {
                array.insert(index, value);
                return this;
            }

            public Inliner<V, A> set(final int index, final V value) {
                array.set(index, value);
                return this;
            }

            public Inliner<V, A> remove(final int index) {
                array.remove(index);
                return this;
            }

            public A array() {
                return array;
            }

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
        Array.Immutable<V> reverseCopy();

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
        default V first() {
            return this.isEmpty() ? null : this.get(0);
        }

        @Override
        default V last() {
            return this.isEmpty() ? null : this.get(this.capacity().decrement().get());
        }

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
        default Array.Immutable<V> values() {
            return this;
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Empty<V>
            extends Array.Immutable<V>, List.Empty<V>, Indexed.Empty<NonNegativeInteger, V> {

        @Override
        default V head() {
            return List.Empty.super.head();
        }

        @Override
        default Stream<V, ? extends Array.Empty<V>> stream() {
            return Streams.empty(this);
        }

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
        default Array.Singleton<V> andSuffix(final V value) {
            return this.andPrefix(value);
        }

        @Override
        default Array.Empty<V> notFirst(final Object value) {
            return this;
        }

        @Override
        default Array.Empty<V> notAll(final Object value) {
            return this;
        }

        @Override
        default Array.Empty<V> reverseCopy() {
            return this;
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
        default V first() {
            return null;
        }

        @Override
        default V last() {
            return null;
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
            return this.andPrefix(value).reverseCopy();
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
        default V first() {
            return List.Singleton.super.first();
        }

        @Override
        default V head() {
            return List.Singleton.super.head();
        }

        @Override
        default V last() {
            return null;
        }

        @Override
        Array.Empty<V> tail();

        @Override
        default Array.Immutable<V> notFirst(final Object value) {
            return Objects.equals(value, this.value()) ? this.tail() : this;
        }

        @Override
        default Immutable<V> segment(final int from, final int to) {
            final int s = this.start().intValue();
            return from == s && to == s + 1
                    ? this
                    : Exceptions.noSuchElement();
        }

        @Override
        default Array.Singleton<V> reverseCopy() {
            return this;
        }

        @Override
        default NonNegativeInteger capacity() {
            return NonNegativeInteger.ONE;
        }

    }

    abstract class Abstract<V> extends List.Abstract<V> implements Array<V> {

        @Override
        public boolean equals(final Object object) {
            return object instanceof Array && this.equals((Array) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

    }

}
