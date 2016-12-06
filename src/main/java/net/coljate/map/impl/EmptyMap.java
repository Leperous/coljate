package net.coljate.map.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.ImmutableCollection;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class EmptyMap<K, V>
        extends AbstractMap<K, V>
        implements ImmutableMap<K, V> {

    @Override
    public Entry<K, V> entry(final Object key) {
        return null;
    }

    @Override
    public ImmutableSet<K> keys() {
        return ImmutableSet.of();
    }

    @Override
    public ImmutableCollection<V> values() {
        return ImmutableCollection.of();
    }

    @Override
    public SingletonMap<K, V> with(final K key, final V value) {
        return SingletonMap.of(key, value);
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
