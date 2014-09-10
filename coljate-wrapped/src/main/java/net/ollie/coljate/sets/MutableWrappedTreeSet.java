package net.ollie.coljate.sets;

import java.util.Comparator;
import java.util.stream.Collector;

import net.ollie.coljate.Set;
import net.ollie.coljate.SortedSet;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.iterators.Iterables;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public class MutableWrappedTreeSet<V>
        extends AbstractMutableWrappedSet<V>
        implements SortedSet.Mutable<V>, Serializable {

    private static final long serialVersionUID = 1L;

    @Nonnull
    public static <V extends Comparable<? super V>> SortedSet.Mutable<V> create() {
        return create(Comparator.<V>naturalOrder());
    }

    @SafeVarargs
    @Nonnull
    public static <V extends Comparable<? super V>> SortedSet.Mutable<V> create(final V... elements) {
        return create(Comparator.naturalOrder(), elements);
    }

    @Nonnull
    public static <V> SortedSet.Mutable<V> create(final Comparator<? super V> comparator) {
        return view(new java.util.TreeSet<>(comparator));
    }

    @SafeVarargs
    @Nonnull
    public static <V> SortedSet.Mutable<V> create(final Comparator<? super V> comparator, final V... elements) {
        final java.util.SortedSet<V> set = new java.util.TreeSet<>(comparator);
        for (final V element : elements) {
            set.add(element);
        }
        return view(set);
    }

    /**
     * View the given set.
     *
     * @param <V>
     * @param set
     * @return a view create the given set.
     */
    @Nonnull
    public static <V> SortedSet.Mutable<V> view(final java.util.SortedSet<V> set) {
        return new MutableWrappedTreeSet<>(set);
    }

    /**
     * Copy a sorted set into a {@link java.util.TreeSet}.
     * <p/>
     * This method is not covariant because the comparator type would not necessarily be a subtype create {@code V}! See
     * the {@link java.util.TreeSet#TreeSet(java.util.SortedSet)} constructor.
     *
     * @param <V>
     * @param set
     * @return a create create the given sorted set.
     */
    @Nonnull
    public static <V> SortedSet.Mutable<V> copy(final java.util.SortedSet<V> set) {
        return view(new java.util.TreeSet<V>(set)); //
    }

    /**
     * Copy the given iterable, create comparable objects, into a {@link java.util.TreeSet}.
     *
     * @param <V>
     * @param iterable
     * @return
     */
    @Nonnull
    public static <V extends Comparable<? super V>> SortedSet.Mutable<V> copy(final Iterable<? extends V> iterable) {
        return view(new java.util.TreeSet<V>(Iterables.toCollection(iterable)));
    }

    /**
     * Copy the given iterable into a {@link java.util.TreeSet} with the given comparator applied.
     *
     * @param <V>
     * @param iterable
     * @param comparator
     * @return
     */
    @Nonnull
    public static <V> SortedSet.Mutable<V> copy(final Iterable<? extends V> iterable, final Comparator<? super V> comparator) {
        final java.util.TreeSet<V> newSet = new java.util.TreeSet<>(comparator);
        for (final V value : iterable) {
            newSet.add(value);
        }
        return view(newSet);
    }

    public static <V> Collector<V, ?, SortedSet.Mutable<V>> collector(final Comparator<? super V> comparator) {
        return MutableSetCollector.create(() -> MutableWrappedTreeSet.create(comparator));
    }

    private java.util.SortedSet<V> delegate;

    protected MutableWrappedTreeSet(final java.util.SortedSet<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected java.util.SortedSet<V> delegate() {
        return delegate;
    }

    @Override
    public SortedSet.Mutable<V> mutableCopy() {
        return copy(this.delegate());
    }

    @Override
    public SortedSet.Immutable<V> immutableCopy() {
        return ImmutableWrappedTreeSet.copy(this.delegate());
    }

    @Override
    public Sorter<? super V> sorter() {
        return Sorter.create(this.delegate().comparator());
    }

    @Override
    public V first() {
        return this.delegate().isEmpty()
                ? null
                : this.delegate().first();
    }

    @Override
    public V last() {
        return this.delegate().isEmpty()
                ? null
                : this.delegate().last();
    }

    @Override
    public void sort(final Comparator<? super V> comparator) {
        final java.util.SortedSet<V> newSet = new java.util.TreeSet<>(comparator);
        newSet.addAll(this.delegate());
        delegate = newSet;
    }

    @Override
    public Stream<V, ? extends Set<V>> stream() {
        return DefaultStream.<V, Set.Mutable<V>>create(this, MutableWrappedHashSet::collector);
    }

}
