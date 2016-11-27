package net.coljate.map;

import java.util.Iterator;

import net.coljate.Collection;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 * @see java.util.HashMap
 */
public class MutableHashMap<K, V> implements HashMap<K, V> {

    @Override
    public Lookup<V> lookup(final Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<K> keys() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableMap<K, V> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
