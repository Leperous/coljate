package net.ollie.coljate.maps;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public abstract class NativeMap<K, V> implements Map<K, V> {

    public static <K, V> java.util.HashMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return new java.util.HashMap<>(map);
    }

    private final java.util.Map<K, V> delegate;

    protected NativeMap(final java.util.Map<K, V> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        return MutableHashMap.copyOf(delegate);
    }

    @Override
    public ImmutableMap<K, V> immutableCopy() {
        return ImmutableHashMap.copyOf(delegate);
    }

    @Override
    public boolean inDomain(final K key) {
        return delegate.containsKey(key);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

}
