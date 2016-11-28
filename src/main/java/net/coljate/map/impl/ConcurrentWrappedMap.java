package net.coljate.map.impl;

import net.coljate.map.ConcurrentMap;

/**
 *
 * @author ollie
 */
public class ConcurrentWrappedMap<K, V>
        extends MutableWrappedMap<K, V>
        implements ConcurrentMap<K, V> {

    public static <K, V> ConcurrentWrappedMap<K, V> createHashMap(final int initialCapacity) {
        return new ConcurrentWrappedMap<>(new java.util.concurrent.ConcurrentHashMap<>(initialCapacity));
    }

    protected ConcurrentWrappedMap(final java.util.concurrent.ConcurrentMap<K, V> delegate) {
        super(delegate);
    }

    @Override
    public ConcurrentMap<K, V> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
