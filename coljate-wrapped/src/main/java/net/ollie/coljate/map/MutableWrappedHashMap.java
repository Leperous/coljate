package net.ollie.coljate.map;

import net.ollie.coljate.Collection;
import net.ollie.coljate.sets.MutableSet;
import net.ollie.coljate.set.MutableWrappedSet;
import net.ollie.coljate.utils.Lazy;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashMap<K, V> extends WrappedHashMap<K, V> implements MutableMap<K, V> {

    public static <K, V> MutableMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new MutableWrappedHashMap<>(copyIntoHashMap(map));
    }

    private final java.util.HashMap<K, V> delegate;
    private final Lazy<MutableSet<K>> keys;

    protected MutableWrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
        this.keys = Lazy.nonNull(() -> new MutableWrappedSet<>(delegate.keySet()));
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

}
