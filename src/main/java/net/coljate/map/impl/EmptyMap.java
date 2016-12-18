package net.coljate.map.impl;

import java.io.Serializable;

import net.coljate.collection.Empty;
import net.coljate.collection.ImmutableCollection;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class EmptyMap<K, V>
        extends AbstractMap<K, V>
        implements Empty<Entry<K, V>>, ImmutableMap<K, V>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final EmptyMap INSTANCE = new EmptyMap();

    public static <K, V> EmptyMap<K, V> instance() {
        return INSTANCE;
    }

    protected EmptyMap() {
    }

    @Override
    public boolean contains(final Object object) {
        return Empty.super.contains(object);
    }

    @Override
    public ImmutableEntry<K, V> entry(final Object key) {
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

}
