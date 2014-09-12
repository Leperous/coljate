package net.ollie.coljate.sets;

import java.util.Comparator;

import net.ollie.coljate.Set;
import net.ollie.coljate.SortedSet;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.streams.DefaultStream;

import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public class ImmutableWrappedTreeSet<V>
        extends ImmutableWrappedSet<V>
        implements SortedSet.Immutable<V> {

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V extends Comparable<? super V>> SortedSet.Empty<V> create() {
        return ImmutableEmptyTreeSet.DEFAULT;
    }

    @Nonnull
    public static <V> SortedSet.Empty<V> create(final Sorter<? super V> sorter) {
        return new ImmutableEmptyTreeSet<>(sorter);
    }

    @Nonnull
    public static <V> SortedSet.Singleton<V> create(final Sorter<? super V> sorter, final V value) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Nonnull
    public static <V extends Comparable<? super V>> SortedSet.Immutable<V> copy(final Iterable<? extends V> iterable) {
        return new ImmutableWrappedTreeSet<>(MutableWrappedTreeSet.copy(iterable));
    }

    @Nonnull
    public static <V> SortedSet.Immutable<V> copy(final Iterable<? extends V> iterable, final Comparator<? super V> comparator) {
        return new ImmutableWrappedTreeSet<>(MutableWrappedTreeSet.copy(iterable, comparator));
    }

    @Nonnull
    public static <V> SortedSet.Immutable<V> copy(final java.util.SortedSet<V> set) {
        return new ImmutableWrappedTreeSet<>(MutableWrappedTreeSet.copy(set));
    }

    private final SortedSet.Mutable<V> underlying;

    protected ImmutableWrappedTreeSet(final SortedSet.Mutable<V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected SortedSet<V> underlying() {
        return underlying;
    }

    @Override
    protected SortedSet.Immutable<V> copyOf(final Set<V> set) {
        return ImmutableWrappedTreeSet.copy(set, this.sorter());
    }

    @Override
    public SortedSet.Mutable<V> mutableCopy() {
        return MutableWrappedTreeSet.copy(this, this.sorter());
    }

    @Override
    public SortedSet.Immutable<V> and(final V value) {
        final SortedSet.Mutable<V> copy = this.mutableCopy();
        return copy.add(value)
                ? this.copyOf(copy)
                : this;
    }

    @Override
    public SortedSet.Immutable<V> not(final Object object) {
        final SortedSet.Mutable<V> copy = this.mutableCopy();
        return copy.remove(object)
                ? this.copyOf(copy)
                : this;
    }

    @Override
    public Sorter<? super V> sorter() {
        return this.underlying().sorter();
    }

    @Override
    public V first() {
        return this.underlying().first();
    }

    @Override
    public V last() {
        return this.underlying().last();
    }

    @Override
    public SortedSet.Immutable<V> sort(final Sorter<? super V> comparator) {
        final SortedSet.Mutable<V> copy = this.underlying().mutableCopy();
        copy.sort(comparator);
        return copy.immutableCopy();
    }

    @Override
    public SortedSet.Immutable<V> tail() {
        return this.immutableCopy().not(this.head());
    }

    @Override
    public Set.Stream<V, ? extends Set<V>> stream() {
        return DefaultStream.create(this, ImmutableWrappedHashSet::collector);
    }

    protected static class ImmutableEmptyTreeSet<V>
            extends ImmutableWrappedTreeSet<V>
            implements SortedSet.Empty<V> {

        @SuppressWarnings({"unchecked", "rawtypes"})
        static final ImmutableEmptyTreeSet DEFAULT = new ImmutableEmptyTreeSet(Sorter.natural());

        protected ImmutableEmptyTreeSet(final Sorter<? super V> sorter) {
            super(MutableWrappedTreeSet.create(sorter));
        }

        @Override
        public SortedSet.Singleton<V> and(final V value) {
            return create(this.sorter(), value);
        }

        @Override
        public SortedSet.Mutable<V> mutableCopy() {
            return MutableWrappedTreeSet.create(this.sorter());
        }

        @Override
        public SortedSet.Empty<V> sort(final Sorter<? super V> comparator) {
            return create(comparator);
        }

        @Override
        public SortedSet.Empty<V> not(final Object object) {
            return this;
        }

        @Override
        public SortedSet.Empty<V> tail() {
            return this;
        }

        @Override
        public Stream<V, ? extends Set.Empty<V>> stream() {
            return SortedSet.Empty.super.stream();
        }

    }

}
