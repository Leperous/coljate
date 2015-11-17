package net.ollie.coljate.maps;

import java.util.Iterator;

import net.ollie.coljate.Collection;
import net.ollie.coljate.sets.MutableNativeSet;
import net.ollie.coljate.sets.MutableSet;
import net.ollie.coljate.utils.Lazy;

/**
 *
 * @author Ollie
 */
public class MutableHashMap<K, V> extends NativeMap<K, V> implements MutableMap<K, V> {

    public static <K, V> MutableMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new MutableHashMap<>(copyIntoHashMap(map));
    }

    private final java.util.HashMap<K, V> delegate;
    private final Lazy<MutableSet<K>> keys;

    protected MutableHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
        this.keys = Lazy.nonNull(() -> new MutableNativeSet<>(delegate.keySet()));
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
    public MutableSet<K> keys() {
        return keys.get();
    }

    @Override
    public MutableSet<? extends MutableMapEntry<K, V>> entries() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Map<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
