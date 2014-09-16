package net.ollie.coljate.lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.sets.SortedSet;
import net.ollie.coljate.intervals.IndexInterval;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.ArrayLists;
import net.ollie.coljate.utils.Arrays;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * @param <V> value type
 * @author Ollie
 */
@NotThreadSafe
public abstract class MutableWrappedArray<V>
        extends Array.Abstract<V>
        implements Array.Mutable<V> {

    public static final int DEFAULT_INITIAL_CAPACITY = 10;

    public static <V> MutableWrappedArray<V> create() {
        return create(DEFAULT_INITIAL_CAPACITY);
    }

    public static <V> MutableWrappedArray<V> create(final NonNegativeInteger initialCapacity) {
        return create(initialCapacity.intValue());
    }

    public static <V> MutableWrappedArray<V> create(final int initialCapacity) {
        return view(new java.util.ArrayList<>(DEFAULT_INITIAL_CAPACITY), initialCapacity);
    }

    @SafeVarargs
    public static <V> MutableWrappedArray<V> create(final V... array) {
        return view(new java.util.ArrayList<>(java.util.Arrays.asList(array)), array.length);
    }

    public static <V> MutableWrappedArray<V> view(final V[] array) {
        return new ArrayBackedArray<>(array);
    }

    public static <V> MutableWrappedArray<V> copy(final Iterable<? extends V> iterable) {
        return view(ArrayLists.copy(iterable));
    }

    public static <V> MutableWrappedArray<V> view(final java.util.ArrayList<V> list) {
        return new MutableListBackedArray<>(list, list.size());
    }

    @Nonnull
    public static <V> MutableWrappedArray<V> view(final java.util.ArrayList<V> list, final int capacity) {
        return new MutableListBackedArray<>(list, capacity);
    }

    @Nonnull
    public static <V> MutableWrappedArray<V> maybeView(final Iterable<V> iterable) {
        return view(ArrayLists.castOrCopy(iterable));
    }

    @Nonnull
    public static <V> Collector<V, ?, ? extends Array<V>> collector() {
        return new ArrayCollector<>();
    }

    @Override
    public Array.Immutable<V> immutableCopy() {
        return ImmutableWrappedArray.copy(this);
    }

    @Override
    public Array.Mutable<V> mutableCopy() {
        return MutableWrappedArray.copy(this);
    }

    static class MutableListBackedArray<V>
            extends MutableWrappedArray<V>
            implements Array.Mutable<V> {

        private final java.util.ArrayList<V> underlying;
        private final int capacity;

        MutableListBackedArray(final java.util.ArrayList<V> underlying, final int capacity) {
            this.underlying = underlying;
            this.capacity = capacity;
        }

        @Override
        public NonNegativeInteger capacity() {
            return NonNegativeInteger.of(capacity);
        }

        @Override
        public NonNegativeInteger indexOf(final Object value) {
            final int index = underlying.indexOf(value);
            return index >= 0
                    ? NonNegativeInteger.of(index)
                    : null;
        }

        @Override
        public V get(final NonNegativeInteger index) {
            return this.get(index.intValue());
        }

        @Override
        public V get(final int index) throws IndexOutOfBoundsException {
            return underlying.get(index);
        }

        @Override
        public V set(final NonNegativeInteger index, final V value) throws IndexOutOfBoundsException {
            return this.set(index.intValue(), value);
        }

        @Override
        public V set(final int index, final V value) throws IndexOutOfBoundsException {
            if (index < capacity && index >= underlying.size()) {
                underlying.addAll(underlying.size(), java.util.Collections.nCopies(capacity - index, null));
            }
            return underlying.set(index, value);
        }

        @Override
        public void insert(final NonNegativeInteger index, final V value) throws IndexOutOfBoundsException {
            this.insert(index.intValue(), value);
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
        public Array.Mutable<V> reverse() {
            Collections.reverse(underlying);
            return this;
        }

        @Override
        public void setCapacity(final NonNegativeInteger size) {
            underlying.ensureCapacity(size.intValue());
        }

        @Override
        public Array<V> segment(final NonNegativeInteger from, final NonNegativeInteger to) {
            return this.segment(from.intValue(), to.intValue());
        }

        @Override
        public Array<V> segment(final int from, final int to) {
            return copy(underlying.subList(from, to));
        }

        @Override
        public Array<V> tail() {
            return copy(underlying.subList(1, underlying.size()));
        }

        @Override
        public NonNegativeInteger count() {
            return NonNegativeInteger.of(this.intCount());
        }

        public int intCount() {
            return underlying.size();
        }

        @Override
        public V last() {
            return this.isEmpty()
                    ? null
                    : this.get(this.intCount() - 1);
        }

        @Override
        public IndexInterval keys() {
            return IndexInterval.lessThan(this.count());
        }

        @Override
        public Object[] toRawArray() {
            return underlying.toArray();
        }

        @Override
        public Stream<V, Array<V>> stream() {
            return DefaultStream.create(underlying, MutableListBackedArray::collector);
        }

        @Override
        public String toString() {
            return underlying.toString();
        }

    }

    static class ArrayBackedArray<V>
            extends MutableWrappedArray<V>
            implements Array.Mutable<V> {

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
        public V get(final NonNegativeInteger index) throws IndexOutOfBoundsException {
            return this.get(index.intValue());
        }

        @Override
        public V get(final int index) throws IndexOutOfBoundsException {
            return array[index];
        }

        @Override
        public void insert(final NonNegativeInteger index, final V value) {
            this.insert(index.intValue(), value);
        }

        @Override
        public void insert(final int index, final V value) {
            array[index] = value;
        }

        @Override
        public V set(final NonNegativeInteger index, final V value) {
            return this.set(index.intValue(), value);
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
            final V[] newArray = java.util.Arrays.copyOf(array, array.length + 1); //TODO can we create in just one step?
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
        public Array.Mutable<V> reverse() {
            for (int i = 0; i < array.length / 2; i++) {
                final V temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
            return this;
        }

        @Override
        public Array.Mutable<V> mutableCopy() {
            return this.clone(this.copyArray());
        }

        @Override
        public Array<V> tail() {
            return this.isEmpty()
                    ? create()
                    : this.clone(java.util.Arrays.copyOfRange(array, 1, array.length));
        }

        public int intCount() {
            return Iterables.doCount(this);
        }

        @Override
        public void clear() {
            for (int i = 0; i < this.intCount(); i++) {
                this.remove(i);
            }
        }

        @Override
        public Array<V> segment(final NonNegativeInteger from, final NonNegativeInteger to) {
            return this.segment(from.intValue(), to.intValue());
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
        public SortedSet<NonNegativeInteger> keys() {
            throw new UnsupportedOperationException("keys() not supported.");
        }

        @Override
        public NonNegativeInteger capacity() {
            return NonNegativeInteger.of(array.length);
        }

        @Override
        public void setCapacity(final NonNegativeInteger capacity) {
            this.array = this.copyArray(capacity.intValue());
        }

        @Override
        public boolean isEmpty() {
            return array.length == 0;
        }

        @Override
        public Stream<V, Array<V>> stream() {
            return DefaultStream.<V, Array<V>>create(Arrays.iterator(array), ArrayBackedArray::collector);
        }

        @Override
        public Iterator<V> iterator() {
            return Arrays.iterator(array);
        }

        @Override
        public String toString() {
            return java.util.Arrays.deepToString(array);
        }

    }

    private static final class ArrayCollector<V>
            implements Collector<V, Array.Mutable<V>, Array.Mutable<V>> {

        @Override
        public Supplier<Array.Mutable<V>> supplier() {
            return MutableWrappedArray::create;
        }

        @Override
        public BiConsumer<Array.Mutable<V>, V> accumulator() {
            return (array, element) -> array.suffix(element);
        }

        @Override
        public BinaryOperator<Array.Mutable<V>> combiner() {
            throw new UnsupportedOperationException("combiner not supported yet!");
        }

        @Override
        public Function<Array.Mutable<V>, Array.Mutable<V>> finisher() {
            return Function.identity();
        }

        @Override
        public java.util.Set<Characteristics> characteristics() {
            return java.util.Collections.singleton(Characteristics.IDENTITY_FINISH);
        }

    }

}
