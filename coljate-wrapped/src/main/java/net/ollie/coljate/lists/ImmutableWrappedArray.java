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
import net.ollie.coljate.sets.ImmutableWrappedTreeSet;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public class ImmutableWrappedArray<V>
        extends ImmutableWrappedList<V>
        implements Array.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    @Nonnull
    public static <V> Array.Empty<V> create() {
        return ImmutableEmptyArray.INSTANCE;
    }

    @Nonnull
    public static <V> Array.Immutable<V> create(final int size) {
        return copy(MutableWrappedArray.create(size));
    }

    /**
     * Unambiguous single-element constructor.
     *
     * @param <V>
     * @param element
     * @return
     */
    @Nonnull
    public static <V> Array.Singleton<V> create(final V element) {
        return new ImmutableSingletonArray<>(element);
    }

    @SafeVarargs
    public static <V> Array.Immutable<V> create(final V... array) {
        switch (array.length) {
            case 0:
                return ImmutableWrappedArray.create();
            default:
                return new ImmutableWrappedArray<>(MutableWrappedArray.create(array));
        }
    }

    @SuppressWarnings("unchecked")
    public static <V> Collector<V, ?, ? extends Array.Immutable<V>> collector() {
        return ImmutableArrayCollector.INSTANCE;
    }

    @SuppressWarnings("unchecked") //Read-only
    public static <V> Array.Immutable<V> copy(final Iterable<? extends V> iterable) {
        return iterable instanceof Array.Immutable
                ? (Array.Immutable<V>) iterable
                : new ImmutableWrappedArray<>(MutableWrappedArray.copy(iterable));
    }

    private final Array<V> underlying;

    protected ImmutableWrappedArray(final Array<V> underlying) {
        super(underlying);
        this.underlying = underlying;
    }

    protected <T> Array.Immutable<T> viewOf(final Array<T> list) {
        return new ImmutableWrappedArray<>(list);
    }

    @Override
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    protected Array<V> underlying() {
        return underlying;
    }

    @Override
    public NonNegativeInteger capacity() {
        return this.underlying().capacity();
    }

    @Override
    public Array.Mutable<V> mutableCopy() {
        return this.underlying().mutableCopy();
    }

    @Override
    public V get(final int index) throws IndexOutOfBoundsException {
        return this.underlying().get(index);
    }

    @Override
    public Array.Immutable<V> sort(final Comparator<? super V> comparator) {
        final Array.Immutable<V> sorted = this.underlying().immutableCopy();
        sorted.sort(comparator);
        return copy(sorted);
    }

    @Override
    public Array.Immutable<V> andPrefix(final V value) {
        return copy(super.andPrefix(value));
    }

    @Override
    public Array.Immutable<V> andSuffix(final V value) {
        return copy(super.andSuffix(value));
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public Array.Immutable<V> notFirst(final Object value) {
        return copy(super.notFirst(value));
    }

    @Override
    public Array.Immutable<V> notAll(final Object value) {
        return copy(super.notAll(value));
    }

    @Override
    public Array.Immutable<V> tail() {
        return copy(super.tail());
    }

    @Override
    public Array.Immutable<V> segment(final int from, final int to) {
        return this.underlying().segment(from, to).immutableCopy();
    }

    @Override
    public NonNegativeInteger indexOf(final Object value) {
        return this.underlying().indexOf(value);
    }

    @Override
    public SortedSet.Immutable<NonNegativeInteger> keys() {
        return this.underlying().keys().immutableCopy();
    }

    @Override
    public Array.Immutable<V> reverse() {
        return this.underlying().mutableCopy().reverse().immutableCopy();
    }

    @Override
    public Array.Immutable<V> toArray() {
        return this;
    }

    @Override
    public Stream<V, ? extends Array<V>> stream() {
        return DefaultStream.create(this, ImmutableWrappedArray::collector);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Array
                && this.equals((Array) object);
    }

    @Override
    public int hashCode() {
        return Iterables.productHashCode(this);
    }

    protected static class ImmutableEmptyArray<V>
            implements Array.Empty<V> {

        static final ImmutableEmptyArray INSTANCE = new ImmutableEmptyArray();

        @Override
        public Array.Singleton<V> andPrefix(final V value) {
            return ImmutableWrappedArray.create(value);
        }

        @Override
        public Array.Mutable<V> mutableCopy() {
            return MutableWrappedArray.create();
        }

        @Override
        public SortedSet.Empty<NonNegativeInteger> keys() {
            return ImmutableWrappedTreeSet.create();
        }

    }

    protected static class ImmutableSingletonArray<V>
            extends ImmutableSingletonList<V>
            implements Array.Singleton<V> {

        protected ImmutableSingletonArray(final V value) {
            super(value);
        }

        @Override
        public SortedSet.Immutable<NonNegativeInteger> keys() {
            return null;
        }

        @Override
        public Array.Immutable<V> notFirst(final Object value) {
            return Objects.equals(this.value(), value)
                    ? create()
                    : this;
        }

        @Override
        public Array.Immutable<V> segment(final int from, final int to) {
            if (from == 0 && to == 0) {
                return create();
            } else if (from == 0 && to == 1) {
                return this;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public Array.Empty<V> tail() {
            return create();
        }

        @Override
        public Array.Immutable<V> andPrefix(final V value) {
            return create(value, this.value());
        }

        @Override
        public Array.Immutable<V> andSuffix(final V value) {
            return create(value, this.value());
        }

        @Override
        public Array.Immutable<V> notAll(final Object value) {
            return Objects.equals(this.value(), value)
                    ? create()
                    : this;
        }

        @Override
        public Array.Mutable<V> mutableCopy() {
            return MutableWrappedArray.create(this.value());
        }

        public Stream<V, ? extends Array.Immutable<V>> stream() {
            return DefaultStream.singleton(this.value(), ImmutableWrappedArray::collector);
        }

    }

    protected static class ImmutableArrayCollector<V>
            implements Collector<V, Array.Mutable<V>, Array.Immutable<V>> {

        @SuppressWarnings("rawtypes")
        static final ImmutableArrayCollector INSTANCE = new ImmutableArrayCollector();

        @Override
        public Supplier<Array.Mutable<V>> supplier() {
            return MutableWrappedArray::create;
        }

        @Override
        public BiConsumer<Array.Mutable<V>, V> accumulator() {
            return Array.Mutable::suffix;
        }

        @Override
        public BinaryOperator<Array.Mutable<V>> combiner() {
            throw new UnsupportedOperationException("combiner not supported yet!");
        }

        @Override
        public Function<Array.Mutable<V>, Array.Immutable<V>> finisher() {
            return Array::immutableCopy;
        }

        @Override
        public java.util.Set<Characteristics> characteristics() {
            return java.util.Collections.emptySet();
        }

    }

}
