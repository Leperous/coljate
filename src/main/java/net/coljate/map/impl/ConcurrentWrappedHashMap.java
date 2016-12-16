package net.coljate.map.impl;

import net.coljate.map.ConcurrentMap;
import net.coljate.map.Entry;

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

    public static <K, V> ConcurrentWrappedHashMap<K, V> copyOf(final java.util.Collection<? extends Entry<? extends K, ? extends V>> entries) {
        final ConcurrentWrappedHashMap<K, V> map = create(entries.size());
        entries.forEach(entry -> map.put(entry.key(), entry.value()));
        return map;
    }

    protected ConcurrentWrappedHashMap(final java.util.concurrent.ConcurrentHashMap<K, V> delegate) {
        super(delegate);
    }

    @Override
    public java.util.concurrent.ConcurrentHashMap<K, V> javaMapCopy() {
        return super.javaMapCopy(java.util.concurrent.ConcurrentHashMap::new);
    }

    @Override
    public ConcurrentWrappedHashMap<K, V> mutableCopy() {
        return new ConcurrentWrappedHashMap<>(this.javaMapCopy());
    }

}
