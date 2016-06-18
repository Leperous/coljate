package net.ollie.coljate.map;

import java.util.Comparator;

import net.ollie.coljate.set.SortedSet;
import net.ollie.coljate.set.WrappedSortedSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedSortedMap<K, V>
        extends MutableWrappedMap<K, V>
        implements MutableSortedMap<K, V> {

    public static <K extends Comparable<? super K>, V> MutableWrappedSortedMap<K, V> create() {
        return create(Comparator.naturalOrder());
    }

    public static <K, V> MutableWrappedSortedMap<K, V> create(final Comparator<? super K> comparator) {
        return new MutableWrappedSortedMap<>(new java.util.TreeMap<>(comparator));
    }

    public static <K, V> MutableWrappedSortedMap<K, V> viewOf(final java.util.NavigableMap<K, V> map) {
        return new MutableWrappedSortedMap<>(map);
    }

    private final java.util.NavigableMap<K, V> delegate;

    MutableWrappedSortedMap(final java.util.NavigableMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public K minKey() {
        return delegate.firstKey();
    }

    @Override
    public K maxKey() {
        return delegate.lastKey();
    }

    @Override
    public SortedSet<K> keys() {
        return new WrappedSortedSet<>(delegate.navigableKeySet(), this.comparator());
    }

    @Override
    public SortedMap<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    @SuppressWarnings("unchecked")
    public Comparator<? super K> comparator() {
        final Comparator<? super K> comparator = delegate.comparator();
        return comparator == null ? (Comparator) Comparator.naturalOrder() : comparator;
    }

}
