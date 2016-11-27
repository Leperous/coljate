package net.coljate.map.impl;

import net.coljate.map.MutableMap;

/**
 *
 * @author ollie
 */
public class MutableWrappedMap<K, V>
        extends WrappedMap<K, V>
        implements MutableMap<K, V> {

    private final java.util.Map<K, V> delegate;

    protected MutableWrappedMap(final java.util.Map<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public V put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public MutableEntry<K, V> entry(final Object key) {
        return delegate.containsKey(key)
                ? new MutableWrappedEntry((K) key)
                : null;
    }

    private final class MutableWrappedEntry implements MutableMap.MutableEntry<K, V> {

        private final K key;

        MutableWrappedEntry(final K key) {
            this.key = key;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return delegate.get(key);
        }

        @Override
        public void setValue(final V value) {
            delegate.put(key, value);
        }

    }

}
