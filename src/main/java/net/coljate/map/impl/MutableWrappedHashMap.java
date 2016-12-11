package net.coljate.map.impl;

import java.io.Serializable;

import net.coljate.map.Entry;

/**
 *
 * @author ollie
 */
public class MutableWrappedHashMap<K, V>
        extends MutableWrappedMap<K, V>
        implements HashMap<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <K, V> MutableWrappedHashMap<K, V> copyOf(final java.util.Collection<? extends Entry<K, V>> entries) {
        final java.util.HashMap<K, V> map = new java.util.HashMap<>(entries.size());
        entries.forEach(entry -> map.put(entry.key(), entry.value()));
        return viewOf(map);
    }

    public static <K, V> MutableWrappedHashMap<K, V> createHashMap(final int initialCapacity) {
        return viewOf(new java.util.HashMap<>(initialCapacity));
    }

    public static <K, V> MutableWrappedHashMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return viewOf(MutableWrappedMap.copyIntoHashMap(map));
    }

    public static <K, V> MutableWrappedHashMap<K, V> viewOf(final java.util.HashMap<K, V> map) {
        return new MutableWrappedHashMap<>(map);
    }

    private final java.util.HashMap<K, V> delegate;

    public MutableWrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    protected java.util.HashMap<K, V> mutableDelegateCopy() {
        return new java.util.HashMap<>(delegate);
    }

    @Override
    public MutableWrappedHashMap<K, V> mutableCopy() {
        return new MutableWrappedHashMap<>(this.mutableDelegateCopy());
    }

}
