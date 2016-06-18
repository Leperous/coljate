package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public class WrappedMapEntry<K, V> implements MutableMapEntry<K, V> {

    private final java.util.Map.Entry<K, V> delegate;

    public WrappedMapEntry(final java.util.Map.Entry<K, V> delegate) {
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
