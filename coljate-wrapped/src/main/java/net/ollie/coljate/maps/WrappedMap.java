package net.ollie.coljate.maps;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public abstract class WrappedMap<K, V> implements Map<K, V> {

    final java.util.Map<K, V> delegate;

    protected WrappedMap(final java.util.Map<K, V> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V get(final Object key) {
        return delegate.get(key);
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
