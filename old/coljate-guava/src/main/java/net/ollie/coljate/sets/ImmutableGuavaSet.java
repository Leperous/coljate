package net.ollie.coljate.sets;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import com.google.common.collect.ImmutableSet;
import java.io.Serializable;
import javax.annotation.Nonnull;

import net.ollie.coljate.UnmodifiableGuavaIterator;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("element-type-mismatch")
public class ImmutableGuavaSet<V>
        extends Set.Abstract<V>
        implements Set.Immutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final ImmutableGuavaSet EMPTY = new ImmutableGuavaSet(ImmutableSet.of());

    @Nonnull
    @SuppressWarnings("unchecked")
    public static <V> ImmutableGuavaSet<V> copy(final V[] array) {
        return array.length == 0 ? EMPTY : copy(java.util.Arrays.asList(array));
    }

    @Nonnull
    public static <V> ImmutableGuavaSet<V> copy(final Iterable<? extends V> iterable) {
        return view(ImmutableSet.copyOf(iterable));
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public static <V> ImmutableGuavaSet<V> view(final ImmutableSet<? extends V> set) {
        return set.isEmpty() ? EMPTY : new ImmutableGuavaSet<>(set);
    }

    @Nonnull
    public static <V> Collector<V, ?, ImmutableGuavaSet<V>> collector() {
        return new ImmutableGuavaSetCollector<>();
    }

    private final ImmutableSet<? extends V> underlying;

    protected ImmutableGuavaSet(final ImmutableSet<? extends V> delegate) {
        this.underlying = delegate;
    }

    @Nonnull
    public ImmutableSet<? extends V> underlying() {
        return underlying;
    }

    @Override
    public Set.Immutable<V> and(final V value) {
        return this.contains(value)
                ? this
                : view(ImmutableSet.<V>builder().addAll(underlying).add(value).build());
    }

    @Override
    public Set.Immutable<V> not(final Object value) {
        if (this.contains(value)) {
            final ImmutableSet.Builder<V> builder = ImmutableSet.builder();
            builder.addAll(Iterables.filter(underlying, e -> !Objects.equals(e, value)));
            return view(builder.build());
        } else {
            return this;
        }
    }

    @Override
    public boolean isEmpty() {
        return underlying.isEmpty();
    }

    @Override
    public boolean contains(final Object object) {
        return underlying.contains(object);
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return UnmodifiableGuavaIterator.of(underlying.iterator());
    }

    @Override
    public Stream<V, ? extends ImmutableGuavaSet<V>> stream() {
        return DefaultStream.create(this, ImmutableGuavaSet::collector);
    }

    @Override
    public Set.Mutable<V> mutableCopy() {
        return MutableWrappedHashSet.copy(underlying);
    }

    @Override
    public ImmutableGuavaSet<V> immutableCopy() {
        return this;
    }

    private static final class ImmutableGuavaSetCollector<V>
            extends AbstractSetCollector<V, Set.Mutable<V>, ImmutableGuavaSet<V>> {

        @Override
        public Supplier<Set.Mutable<V>> supplier() {
            return MutableWrappedHashSet::create;
        }

        @Override
        public Function<Set.Mutable<V>, ImmutableGuavaSet<V>> finisher() {
            return ImmutableGuavaSet::copy;
        }

    }

}
