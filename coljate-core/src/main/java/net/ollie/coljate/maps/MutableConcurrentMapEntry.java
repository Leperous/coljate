package net.ollie.coljate.maps;


import javax.annotation.concurrent.NotThreadSafe;

/**
 *
 * @author Ollie
 */
@NotThreadSafe
public class MutableConcurrentMapEntry<K, V> extends AbstractMapEntry<K, V> implements Map.Mutable.Entry<K, V> {

    public static <K, V> Map.Mutable.Entry<K, V> copy(final Map.Entry<? extends K, ? extends V> entry) {
        return new MutableConcurrentMapEntry<>(entry.key(), entry.value());
    }

    private volatile V value;

    public MutableConcurrentMapEntry(final K key, final V value) {
        super(key);
        this.value = value;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public void setValue(final V value) {
        this.value = value;
    }

    @Override
    public Map.Mutable.Entry<K, V> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public Map.Immutable.Entry<K, V> immutableCopy() {
        throw new UnsupportedOperationException("immutableCopy not supported yet!");
    }

}
