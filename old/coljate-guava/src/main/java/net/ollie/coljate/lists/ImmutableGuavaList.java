package net.ollie.coljate.lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.UnmodifiableGuavaIterator;
import net.ollie.coljate.intervals.IndexInterval;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Arrays;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class ImmutableGuavaList<V>
        extends Array.Abstract<V>
        implements Array.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    @Nonnull
    public static <V> ImmutableGuavaList<V> create() {
        return view(ImmutableList.of());
    }

    @Nonnull
    public static <V> ImmutableGuavaList<V> copy(final V[] array) {
        return copy(Arrays.toList(array));
    }

    @Nonnull
    public static <V> ImmutableGuavaList<V> copy(final Iterable<? extends V> iterable) {
        return view(ImmutableList.copyOf(iterable));
    }

    @Nonnull
    public static <V> ImmutableGuavaList<V> build(final ImmutableList.Builder<? extends V> builder) {
        return view(builder.build());
    }

    @Nonnull
    public static <V> ImmutableGuavaList<V> view(final ImmutableList<? extends V> list) {
        return new ImmutableGuavaList<>(list);
    }

    @Nonnull
    public static <V> Collector<V, ?, ImmutableGuavaList<V>> collector() {
        return new ImmutableGuavaListCollector<>();
    }

    private final ImmutableList<? extends V> underlying;

    protected ImmutableGuavaList(final ImmutableList<? extends V> underlying) {
        this.underlying = underlying;
    }

    @Override
    public V get(int index) throws IndexOutOfBoundsException {
        return underlying.get(index);
    }

    @Override
    public boolean isEmpty() {
        return underlying.isEmpty();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return underlying.contains(object);
    }

    @Override
    public Array.Immutable<V> segment(final int from, final int to) {
        return view(underlying.subList(from, to));
    }

    @Override
    public Array.Immutable<V> reverseCopy() {
        return view(underlying.reverse());
    }

    @Override
    public Array.Immutable<V> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public Array.Immutable<V> andPrefix(final V value) {
        return build(ImmutableList.<V>builder().add(value).addAll(underlying));
    }

    @Override
    public Array.Immutable<V> andSuffix(final V value) {
        return build(ImmutableList.<V>builder().addAll(underlying).add(value));
    }

    @Override
    public Array.Immutable<V> notFirst(final Object value) {
        final ImmutableList.Builder<V> builder = ImmutableList.builder();
        boolean removed = false;
        for (final V element : underlying) {
            if (Objects.equals(element, value) && !removed) {
                removed = true;
            } else {
                builder.add(element);
            }
        }
        return build(builder);
    }

    @Override
    public Array.Immutable<V> notAll(final Object value) {
        final ImmutableList.Builder<V> builder = ImmutableList.builder();
        builder.addAll(Iterables.filter(underlying, e -> !Objects.equals(e, value)));
        return build(builder);
    }

    @Override
    public NonNegativeInteger capacity() {
        return NonNegativeInteger.of(underlying.size());
    }

    @Override
    public IndexInterval keys() {
        return IndexInterval.lessThan(this.count());
    }

    @Override
    public Array.Immutable<V> sort(final Comparator<? super V> comparator) {
        final java.util.List<V> sorted = new java.util.ArrayList<>(underlying);
        Collections.sort(sorted, comparator);
        return view(ImmutableList.copyOf(sorted));
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return UnmodifiableGuavaIterator.of(underlying.iterator());
    }

    @Override
    public Stream<V, ? extends Array<V>> stream() {
        return DefaultStream.create(this, ImmutableGuavaList::collector);
    }

    @Override
    public Array.Mutable<V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public ImmutableGuavaList<V> immutableCopy() {
        return this;
    }

    private static final class ImmutableGuavaListCollector<V>
            extends AbstractArrayCollector<V, ImmutableGuavaList<V>> {

        @Override
        public Supplier<Array.Mutable<V>> supplier() {
            return MutableWrappedArray::create;
        }

        @Override
        public Function<Array.Mutable<V>, ImmutableGuavaList<V>> finisher() {
            return ImmutableGuavaList::copy;
        }

    }

}
