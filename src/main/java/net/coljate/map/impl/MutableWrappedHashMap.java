package net.coljate.map.impl;

import java.io.Serializable;

/**
 *
 * @author ollie
 */
public class MutableWrappedHashMap<K, V>
        extends MutableWrappedMap<K, V>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static <K, V> MutableWrappedHashMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return viewOf(copyIntoHashMap(map));
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
