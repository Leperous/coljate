package net.ollie.coljate.map;

import java.util.Iterator;
import java.util.Map;

import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashMap<K, V>
        extends WrappedHashMap<K, V>
        implements MutableMap<K, V> {

    public static final int DEFAULT_CAPACITY = 10;

    public static <K, V> MutableWrappedHashMap<K, V> create() {
        return create(DEFAULT_CAPACITY);
    }

    public static <K, V> MutableWrappedHashMap<K, V> create(final int initialCapacity) {
        return new MutableWrappedHashMap<>(new java.util.HashMap<>(initialCapacity));
    }

    public static <K, V> MutableWrappedHashMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new MutableWrappedHashMap<>(copyIntoHashMap(map));
    }

    public static <K, V> MutableWrappedHashMap<K, V> viewOf(final java.util.HashMap<K, V> map) {
        return new MutableWrappedHashMap<>(map);
    }

    private final java.util.HashMap<K, V> delegate;

    protected MutableWrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public V put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V delete(final Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Iterator<? extends MutableMapEntry<K, V>> entries() {
        return Iterators.transform(delegate.entrySet().iterator(), EntryWrapper::new);
    }

    private final class EntryWrapper implements MutableMapEntry<K, V> {

        private final java.util.Map.Entry<K, V> delegate;

        EntryWrapper(final Map.Entry<K, V> delegate) {
            this.delegate = delegate;
        }

        @Override
        public K key() {
            return delegate.getKey();
        }

        @Override
        public V value() {
            return delegate.getValue();
        }

        @Override
        public V setValue(final V value) {
            return delegate.setValue(value);
        }

    }

}
