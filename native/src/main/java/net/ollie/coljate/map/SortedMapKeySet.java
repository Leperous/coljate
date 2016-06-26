package net.ollie.coljate.map;

import java.util.Comparator;
import java.util.Iterator;

import net.ollie.coljate.set.ImmutableSortedSet;
import net.ollie.coljate.set.MutableSortedSet;
import net.ollie.coljate.set.SortedSet;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class SortedMapKeySet<K> implements SortedSet<K> {

    private final SortedMap<K, ?> delegate;

    protected SortedMapKeySet(final SortedMap<K, ?> delegate) {
        this.delegate = delegate;
    }

    @Override
    public K min() {
        return delegate.minKey();
    }

    @Override
    public K max() {
        return delegate.maxKey();
    }

    @Override
    public SortedSet<K> subSet(final K min, final K max) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public SortedSet<K> tail() {
        return delegate.tail().keys();
    }

    @Override
    public Iterator<K> iterator() {
        return Iterators.transform(delegate.iterator(), KeyValue::key);
    }

    @Override
    public boolean contains(final Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public Comparator<? super K> comparator() {
        return delegate.comparator();
    }

    @Override
    public MutableSortedSet<K> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableSortedSet<K> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

}
