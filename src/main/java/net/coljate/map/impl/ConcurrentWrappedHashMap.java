package net.coljate.map.impl;

import net.coljate.map.ConcurrentMap;

/**
 *
 * @author ollie
 */
public class ConcurrentWrappedHashMap<K, V>
        extends MutableWrappedMap<K, V>
        implements HashMap<K, V>, ConcurrentMap<K, V> {

    public static <K, V> ConcurrentWrappedHashMap<K, V> create(final int initialCapacity) {
        return new ConcurrentWrappedHashMap<>(new java.util.concurrent.ConcurrentHashMap<>(initialCapacity));
    }

    protected ConcurrentWrappedHashMap(final java.util.concurrent.ConcurrentHashMap<K, V> delegate) {
        super(delegate);
    }

    @Override
    public ConcurrentWrappedHashMap<K, V> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
