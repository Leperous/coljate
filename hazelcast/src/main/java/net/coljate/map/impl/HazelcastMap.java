package net.coljate.map.impl;

import com.hazelcast.core.IMap;

import net.coljate.map.ConcurrentMap;

/**
 *
 * @author ollie
 */
public class HazelcastMap<K, V> extends MutableWrappedMap<K, V> implements ConcurrentMap<K, V> {

    public HazelcastMap(final IMap<K, V> delegate) {
        super(delegate);
    }

    @Override
    public ConcurrentMap<K, V> mutableCopy() {
        return ConcurrentMap.super.mutableCopy();
    }

}
