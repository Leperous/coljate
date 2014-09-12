package net.ollie.coljate.sets;

import java.util.Collection;
import java.util.stream.Collector;

import net.ollie.coljate.Set;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.HashSets;

/**
 * @author Ollie
 * @see ImmutableWrappedHashSet
 */
public class MutableWrappedHashSet<V>
        extends AbstractMutableWrappedSet<V> {

    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_CAPACITY = 10;

    public static <V> MutableWrappedHashSet<V> create() {
        return create(DEFAULT_CAPACITY);
    }

    public static <V> MutableWrappedHashSet<V> create(final int initialCapacity) {
        return new MutableWrappedHashSet<>(new java.util.HashSet<>(initialCapacity));
    }

    @SafeVarargs
    public static <V> MutableWrappedHashSet<V> create(final V... array) {
        return new MutableWrappedHashSet<>(HashSets.copy(array));
    }

    public static <V> MutableWrappedHashSet<V> view(final java.util.Set<V> set) {
        return new MutableWrappedHashSet<>(set);
    }

    public static <V> MutableWrappedHashSet<V> copy(final Iterable<? extends V> iterable) {
        return new MutableWrappedHashSet<>(HashSets.copy(iterable));
    }

    @SafeVarargs
    public static <V> MutableWrappedHashSet<V> combine(final Iterable<? extends V>... iterables) {
        final MutableWrappedHashSet<V> set = MutableWrappedHashSet.create();
        for (final Iterable<? extends V> iterable : iterables) {
            set.addAll(iterable);
        }
        return set;
    }

    public static <V> Collector<V, ?, Set.Mutable<V>> collector() {
        return MutableSetCollector.create(MutableWrappedHashSet::create);
    }

    private final java.util.Set<V> delegate;

    protected MutableWrappedHashSet(final java.util.Set<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Collection<V> delegate() {
        return delegate;
    }

    @Override
    public Set.Mutable<V> mutableCopy() {
        return MutableWrappedHashSet.copy(this.delegate());
    }

    @Override
    public ImmutableWrappedHashSet<V> immutableCopy() {
        return ImmutableWrappedHashSet.copy(this.delegate());
    }

    @Override
    public Stream<V, ? extends Set<V>> stream() {
        return DefaultStream.create(this, MutableWrappedHashSet::collector);
    }

}
