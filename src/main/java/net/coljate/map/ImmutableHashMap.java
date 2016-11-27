package net.coljate.map;

import net.coljate.ImmutableCollection;
import net.coljate.UnmodifiableIterator;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class ImmutableHashMap<K, V> implements HashMap<K, V>, ImmutableMap<K, V> {

    @Override
    public Lookup<V> lookup(final Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableHashMap<K, V> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableSet<K> keys() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableCollection<V> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
