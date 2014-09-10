package net.ollie.coljate.lists;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.Array;
import net.ollie.coljate.SortedSet;
import net.ollie.coljate.lists.AbstactMutableWrappedArray.CopiedIntoMutableListBackedArray;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.ArrayLists;
import net.ollie.coljate.utils.Arrays;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import java.io.Serializable;

/**
 * An immutable array, backed by a real Java array.
 *
 * @author Ollie
 */
public class ImmutableArray<V> implements Array.Immutable<V>, CopiedIntoMutableListBackedArray<V>, Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    public static <V> Array.Empty<V> create() {
        return ImmutableEmptyArray.INSTANCE;
    }

    public static <V> Array.Singleton<V> create(final V value) {
        return new ImmutableSingletonArray<>(value);
    }

    public static <V> Array.Immutable<V> copy(final V[] array) {
        final Object[] copy = Arrays.copy(array);
        return copy.length == 0 ? create() : new ImmutableArray<>(copy);
    }

    public static <V> Array.Immutable<V> copy(final Iterable<? extends V> iterable) {
        final java.util.Collection<? extends V> collection = ArrayLists.castOrCopy(iterable);
        final Object[] array = collection.toArray();
        return array.length == 0 ? create() : new ImmutableArray<>(array);
    }

    @SuppressWarnings("unchecked")
    public static <V> Collector<V, ?, Array.Immutable<V>> collector() {
        return ImmutableArrayCollector.INSTANCE;
    }

    private final Object[] array;

    protected ImmutableArray(final Object[] array) {
        this.array = array;
    }

    protected Array.Immutable<V> copyOf(final Object[] array) {
        return array.length == 0 ? create() : new ImmutableArray<>(array);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(final int index) throws IndexOutOfBoundsException {
        return (V) array[index];
    }

    @Override
    public NonNegativeInteger capacity() {
        return NonNegativeInteger.of(array.length);
    }

    @Override
    public V last() {
        throw new UnsupportedOperationException("last not supported yet!");
    }

    @Override
    @SuppressWarnings("unchecked")
    public V head() {
        return (V) array[0];
    }

    @Override
    public boolean isEmpty() {
        return this.capacity().isZero();
    }

    @Override
    public Object[] toRawArray() {
        final Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return (UnmodifiableIterator<V>) UnmodifiableIterator.of(Arrays.iterator(array));
    }

    @Override
    public Stream<V, ? extends Array<V>> stream() {
        return DefaultStream.create(this, ImmutableArray::collector);
    }

    @Override
    public Array.Immutable<V> sort(final Comparator<? super V> comparator) {
        throw new UnsupportedOperationException("sort not supported yet!");
    }

    @Override
    public SortedSet.Immutable<NonNegativeInteger> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public Array.Immutable<V> segment(final int from, final int to) {
        final Object[] segment = new Object[to - from];
        System.arraycopy(array, from, segment, 0, to - from);
        return this.copyOf(segment);
    }

    @Override
    public Array.Immutable<V> reverse() {
        final Object[] reversed = this.toRawArray();
        for (int i = 0; i < reversed.length / 2; i++) {
            final Object temp = reversed[i];
            reversed[i] = reversed[reversed.length - i - 1];
            reversed[reversed.length - i - 1] = temp;
        }
        return this.copyOf(reversed);
    }

    @Override
    public Array.Immutable<V> tail() {
        if (this.isEmpty()) {
            return this;
        }
        final Object[] tail = new Object[array.length - 1];
        System.arraycopy(array, 1, tail, 0, array.length - 1);
        return this.copyOf(tail);
    }

    @Override
    public Array.Immutable<V> andPrefix(final V value) {
        final Object[] prefixed = new Object[array.length + 1];
        prefixed[0] = value;
        System.arraycopy(array, 0, prefixed, 1, array.length);
        return new ImmutableArray<>(prefixed);
    }

    @Override
    public Array.Immutable<V> andSuffix(final V value) {
        final Object[] suffixed = new Object[array.length + 1];
        suffixed[suffixed.length - 1] = value;
        System.arraycopy(array, 0, suffixed, 0, array.length);
        return this.copyOf(suffixed);
    }

    @Override
    public Array.Immutable<V> notFirst(final Object value) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], value)) {
                final Object[] shortened = new Object[array.length - 1];
                System.arraycopy(array, 0, shortened, 0, i);
                System.arraycopy(array, i + 1, shortened, i, array.length - i - 1);
                return this.copyOf(shortened);
            }
        }
        return this;
    }

    @Override
    public Array.Immutable<V> notAll(final Object value) {
        final Object[] not = new Object[array.length];
        int i = 0;
        for (final Object object : array) {
            if (!Objects.equals(object, value)) {
                not[i++] = object;
            }
        }
        final Object[] shortened;
        if (i < array.length) {
            shortened = new Object[i];
            System.arraycopy(not, 0, shortened, 0, i);
        } else {
            shortened = not;
        }
        return this.copyOf(shortened);
    }

    @Override
    public Array.Mutable<V> mutableCopy() {
        return CopiedIntoMutableListBackedArray.super.mutableCopy();
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof Array && this.equals((Array<?>) that);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

    @Override
    public String toString() {
        return java.util.Arrays.deepToString(array);
    }

    private static final class ImmutableEmptyArray<V>
            implements CopiedIntoMutableListBackedArray<V>, Array.Empty<V>, Serializable {

        private static final long serialVersionUID = 1L;

        @SuppressWarnings("rawtypes")
        static final ImmutableEmptyArray INSTANCE = new ImmutableEmptyArray();

        @Override
        public SortedSet.Empty<NonNegativeInteger> keys() {
            throw new UnsupportedOperationException("keys not supported yet!"); //TODO
        }

        @Override
        public Array.Singleton<V> andPrefix(final V value) {
            return create(value);
        }

        @Override
        public Array.Mutable<V> mutableCopy() {
            return CopiedIntoMutableListBackedArray.super.mutableCopy();
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override
        public String toString() {
            return "[]";
        }

    }

    private static final class ImmutableSingletonArray<V>
            implements CopiedIntoMutableListBackedArray<V>, Array.Singleton<V> {

        private final V value;

        ImmutableSingletonArray(final V value) {
            this.value = value;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public SortedSet.Immutable<NonNegativeInteger> keys() {
            throw new UnsupportedOperationException("keys not supported yet!");
        }

        @Override
        public Array.Immutable<V> andPrefix(final V value) {
            final Object[] prefixed = new Object[2];
            prefixed[0] = value;
            prefixed[1] = this.value;
            return new ImmutableArray<>(prefixed);
        }

        @Override
        public Array.Immutable<V> andSuffix(final V value) {
            final Object[] suffixed = new Object[2];
            suffixed[0] = this.value;
            suffixed[1] = value;
            return new ImmutableArray<>(suffixed);
        }

        @Override
        public Array.Empty<V> tail() {
            return create();
        }

        @Override
        public Stream<V, ? extends Array<V>> stream() {
            return DefaultStream.create(this, ImmutableArray::collector);
        }

        @Override
        public Array.Mutable<V> mutableCopy() {
            return CopiedIntoMutableListBackedArray.super.mutableCopy();
        }

        @Override
        public String toString() {
            return "[" + value + ']';
        }

    }

    private static final class ImmutableArrayCollector<V>
            implements Collector<V, java.util.ArrayList<V>, Array.Immutable<V>>, Serializable {

        private static final long serialVersionUID = 1L;

        @SuppressWarnings("rawtypes")
        static final ImmutableArrayCollector INSTANCE = new ImmutableArrayCollector();

        @Override
        public Supplier<java.util.ArrayList<V>> supplier() {
            return java.util.ArrayList::new;
        }

        @Override
        public BiConsumer<java.util.ArrayList<V>, V> accumulator() {
            return java.util.ArrayList::add;
        }

        @Override
        public BinaryOperator<java.util.ArrayList<V>> combiner() {
            return (l, r) -> {
                l.addAll(r);
                return l;
            };
        }

        @Override
        public Function<java.util.ArrayList<V>, Immutable<V>> finisher() {
            return ImmutableArray::copy;
        }

        @Override
        public java.util.Set<Characteristics> characteristics() {
            return java.util.Collections.emptySet();
        }

    }

}
