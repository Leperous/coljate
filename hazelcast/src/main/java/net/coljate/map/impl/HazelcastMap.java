package net.coljate.map.impl;

import com.hazelcast.core.IMap;


/**
 *
 * @author ollie
 */
public class HazelcastMap<K, V> extends ConcurrentWrappedMap<K, V> {

    public HazelcastMap(final IMap<K, V> delegate) {
        super(delegate);
    }

}
