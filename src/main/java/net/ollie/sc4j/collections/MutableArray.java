package net.ollie.sc4j.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Array;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Unique;
import net.ollie.sc4j.utils.ArrayLists;
import net.ollie.sc4j.utils.Arrays;
import net.ollie.sc4j.utils.Iterables;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author Ollie
 * @param <V> value type
 */
@NotThreadSafe
public abstract class MutableArray<V>
        extends AbstractArray<V>
        implements Array.Mutable<V> {

    public static <V> Array.Mutable<V> empty(final int initialCapacity) {
        return view(new java.util.ArrayList<>(initialCapacity));
    }

    public static <V> Array.Mutable<V> copy(final V[] array) {
        return view(java.util.Arrays.asList(array));
    }

    public static <V> Array.Mutable<V> view(final V[] array) {
        return new ArrayBackedArray<>(array);
    }

    public static <V> Array.Mutable<V> copy(final Iterable<? extends V> iterable) {
        return view(ArrayLists.copy(iterable));
    }

    public static <V> Array.Mutable<V> view(final java.util.List<V> list) {
        return new MutableListBackedArray<>(list);
    }

    static class MutableListBackedArray<V>
            extends MutableArray<V>
            implements Array.NonThreadSafeArray<V> {

        private final java.util.List<V> underlying;

        MutableListBackedArray(final java.util.List<V> underlying) {
            this.underlying = underlying;
        }

        @Override
        public OptionalInt indexOf(final Object value) {
            final int index = underlying.indexOf(value);
            return index >= 0
                    ? OptionalInt.of(index)
                    : OptionalInt.empty();
        }

        @Override
        public V get(int index) throws IndexOutOfBoundsException {
            return underlying.get(index);
        }

        @Override
        public V set(final int index, final V value) throws IndexOutOfBoundsException {
            return underlying.set(index, value);
        }

        @Override
        public void insert(final int index, final V value) throws IndexOutOfBoundsException {
            underlying.set(index, value);
        }

        @Override
        public V remove(final int index) throws IndexOutOfBoundsException {
            return underlying.remove(index);
        }

        @Override
        public void clear() {
            underlying.clear();
        }

        @Override
        public Iterator<V> iterator() {
            return underlying.iterator();
        }

        @Override
        public void prefix(final V value) {
            underlying.add(0, value);
        }

        @Override
        public void prefixAll(final Iterable<? extends V> values) {
            underlying.addAll(0, Iterables.toCollection(values));
        }

        @Override
        public void suffix(final V value) {
            underlying.add(value);
        }

        @Override
        public void suffixAll(final Iterable<? extends V> values) {
            underlying.addAll(Iterables.toCollection(values));
        }

        @Override
        @SuppressWarnings("element-type-mismatch")
        public boolean removeFirst(final Object object) {
            return underlying.remove(object);
        }

        @Override
        public void sort(final Comparator<? super V> comparator) {
            Collections.sort(underlying, comparator);
        }

        @Override
        public Array.Immutable<V> immutable() {
            return ImmutableArray.copy(underlying);
        }

        @Override
        public Array.Mutable<V> mutable() {
            return MutableArray.copy(underlying);
        }

        @Override
        public void resize(final int size) {
            if (underlying instanceof java.util.ArrayList) {
                ((java.util.ArrayList) underlying).ensureCapacity(size);
            }
        }

        @Override
        public <V2> Array.Mutable<V2> map(Function<? super V, ? extends V2> function) {
            throw new UnsupportedOperationException("map() not supported.");
        }

        @Override
        public Array<V> segment(final int from, final int to) {
            throw new UnsupportedOperationException("segment() not supported.");
        }

        @Override
        public Array<V> tail() {
            throw new UnsupportedOperationException("tail() not supported.");
        }

        @Override
        public Array<V> filter(Predicate<? super V> predicate) {
            return MutableArray.copy(Iterables.filter(this, predicate));
        }

        @Override
        public V first() {
            return this.isEmpty()
                    ? null
                    : this.get(0);
        }

        @Override
        public V last() {
            return this.isEmpty()
                    ? null
                    : this.get(this.size() - 1);
        }

        @Override
        public Unique<Integer> keys() {
            throw new UnsupportedOperationException("keys() not supported.");
        }

        @Override
        public <K2> Keyed<K2, V> mapKeys(Function<? super Integer, ? extends K2> function) {
            throw new UnsupportedOperationException("mapKeys() not supported.");
        }

        @Override
        public Array.Mutable<V> filterKeys(Predicate<? super Integer> predicate) {
            throw new UnsupportedOperationException("filterKeys() not supported.");
        }

        @Override
        public Object[] toRawArray() {
            return underlying.toArray();
        }

    }

    static class ArrayBackedArray<V>
            extends MutableArray<V>
            implements Array.NonThreadSafeArray<V> {

        private V[] array;

        ArrayBackedArray(final V[] array) {
            this.array = array;
        }

        V[] copyArray() {
            return this.copyArray(array.length);
        }

        V[] copyArray(final int size) {
            return java.util.Arrays.copyOf(array, size);
        }

        <T> Array.Mutable<T> clone(final T[] array) {
            return new ArrayBackedArray<>(array);
        }

        @Override
        public void resize(final int size) {
            this.array = this.copyArray(size);
        }

        @Override
        public V get(final int index) throws IndexOutOfBoundsException {
            return array[index];
        }

        @Override
        public void insert(final int index, final V value) throws IndexOutOfBoundsException {
            array[index] = value;
        }

        @Override
        public V set(final int index, final V value) throws IndexOutOfBoundsException {
            final V previous = array[index];
            array[index] = value;
            return previous;
        }

        @Override
        public V remove(final int index) throws IndexOutOfBoundsException {
            final V removed = array[index];
            array[index] = null;
            return removed;
        }

        @Override
        public void prefix(final V value) {
            final V[] newArray = java.util.Arrays.copyOf(array, array.length + 1); //TODO can we copy in just one step?
            System.arraycopy(array, 0, newArray, 1, array.length);
            newArray[0] = value;
            this.array = newArray;
        }

        @Override
        public void prefixAll(final Iterable<? extends V> values) {
            this.array = Arrays.concatenate(values, array);
        }

        @Override
        public void suffix(final V value) {
            final V[] newArray = java.util.Arrays.copyOf(array, array.length + 1);
            newArray[newArray.length - 1] = value;
            this.array = newArray;
        }

        @Override
        public void suffixAll(final Iterable<? extends V> values) {
            this.array = Arrays.concatenate(array, values);
        }

        @Override
        public void sort(final Comparator<? super V> comparator) {
            java.util.Arrays.sort(array, comparator);
        }

        @Override
        public Array.Mutable<V> mutable() {
            return this.clone(this.copyArray());
        }

        @Override
        public Array.Immutable<V> immutable() {
            return ImmutableArray.copy(this);
        }

        @Override
        public Array<V> tail() {
            throw new UnsupportedOperationException("tail() not supported.");
        }

        @Override
        public void clear() {
            for (int i = 0; i < this.size(); i++) {
                this.remove(i);
            }
        }

        @Override
        public Array<V> segment(final int from, final int to) {
            return this.clone(java.util.Arrays.copyOfRange(array, from, to));
        }

        @Override
        public Object[] toRawArray() {
            return this.copyArray();
        }

        @Override
        public <V2> Array<V2> map(final Function<? super V, ? extends V2> function) {
            final Array.Mutable<V2> mapped = MutableArray.empty(this.size());
            for (final V element : array) {
                mapped.suffix(function.apply(element));
            }
            return mapped;
        }

        @Override
        public <K2> Keyed<K2, V> mapKeys(final Function<? super Integer, ? extends K2> function) {
            throw new UnsupportedOperationException("mapKeys() not supported.");
        }

        @Override
        public Array<V> filter(final Predicate<? super V> predicate) {
            return MutableArray.copy(Iterables.filter(this, predicate));
        }

        @Override
        public Array.Mutable<V> filterKeys(final Predicate<? super Integer> predicate) {
            throw new UnsupportedOperationException("filterKeys() not supported.");
        }

        @Override
        public Iterator<V> iterator() {
            return Arrays.iterator(array);
        }

        @Override
        public Unique<Integer> keys() {
            throw new UnsupportedOperationException("keys() not supported.");
        }

        @Override
        public int size() {
            return array.length;
        }

        @Override
        public boolean isEmpty() {
            return array.length == 0;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + java.util.Arrays.deepToString(array);
        }

    }

}
