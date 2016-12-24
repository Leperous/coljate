package net.coljate.map.impl;

import net.coljate.collection.Collection;
import net.coljate.map.AbstractMap;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public abstract class DelegatedMutableMap<K, V>
        extends AbstractMap<K, V>
        implements MutableMap<K, V> {

    private final MutableMap<K, V> map;

    protected DelegatedMutableMap(final MutableMap<K, V> map) {
        this.map = map;
    }

    @Override
    public Set<K> keys() {
        return map.keys();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public V put(final K key, final V value) {
        return map.put(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    @Override
    public MutableEntry<K, V> getEntry(Object key) {
        return map.getEntry(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
