package net.ollie.coljate.maps;


import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableWrappedMap<K, V>
        implements Map.Mutable<K, V> {

    @Nonnull
    protected abstract java.util.Map<K, V> delegate();

    @Override
    @SuppressWarnings({"element-type-mismatch", "SuspiciousMethodCalls"})
    public V get(final Object key) {
        return this.delegate().get(key);
    }

    @Override
    public V put(final K key, final V value) {
        return this.delegate().put(key, value);
    }

    @Override
    public V putIfAbsent(final K key, final V value) {
        return this.delegate().putIfAbsent(key, value);
    }

    @Override
    @SuppressWarnings({"element-type-mismatch", "SuspiciousMethodCalls"})
    public V remove(final Object key) {
        return this.delegate().remove(key);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return this.delegate().replace(key, oldValue, newValue);
    }

    @Override
    public void clear() {
        this.delegate().clear();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate().isEmpty();
    }

    protected Map.Mutable.Entry<K, V> convert(final java.util.Map.Entry<K, V> entry) {
        return new MutableEntry<>(entry);
    }

    protected static class MutableEntry<K, V>
            extends Entries<K, V>
            implements Map.Mutable.Entry<K, V> {

        private final java.util.Map.Entry<K, V> delegate;

        public MutableEntry(final java.util.Map.Entry<K, V> delegate) {
            super(delegate.getKey());
            this.delegate = delegate;
        }

        @Override
        public V value() {
            return delegate.getValue();
        }

        @Override
        public void setValue(final V value) {
            delegate.setValue(value);
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof Map.Entry
                    && this.equals((Map.Entry) object);
        }

        @Override
        public int hashCode() {
            return this.hash();
        }

    }

}
