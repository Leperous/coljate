package net.ollie.coljate.maps;

import net.ollie.coljate.Map;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.access.Streamable;

/**
 *
 * @author Ollie
 */
public class ConcurrentWrappedHashMap<K, V>
        extends AbstractMutableWrappedMap<K, V>
        implements Map.Concurrent<K, V> {

    public static <K, V> ConcurrentWrappedHashMap<K, V> create() {
        return view(new java.util.concurrent.ConcurrentHashMap<>());
    }

    public static <K, V> ConcurrentWrappedHashMap<K, V> copy(final java.util.Map<? extends K, ? extends V> map) {
        return view(new java.util.concurrent.ConcurrentHashMap<>(map));
    }

    public static <K, V> ConcurrentWrappedHashMap<K, V> view(final java.util.concurrent.ConcurrentHashMap<K, V> map) {
        return new ConcurrentWrappedHashMap<>(map);
    }

    private final java.util.concurrent.ConcurrentHashMap<K, V> delegate;

    protected ConcurrentWrappedHashMap(java.util.concurrent.ConcurrentHashMap<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected java.util.concurrent.ConcurrentMap<K, V> delegate() {
        return delegate;
    }

    @Override
    public Map.Concurrent<K, V> mutableCopy() {
        return copy(delegate);
    }

    @Override
    public Map.Immutable<K, V> immutableCopy() {
        return ImmutableWrappedHashMap.copy(delegate);
    }

    @Override
    public Map<K, V> putAll(final Map<K, V> map) {
        if (map.isEmpty()) {
            return ImmutableWrappedHashMap.create();
        }
        throw new UnsupportedOperationException("putAll not supported yet!");
    }

    @Override
    public Set.Concurrent<K> keys() {
        throw new UnsupportedOperationException("keys not supported yet!");
    }

    @Override
    public Streamable<V> values() {
        throw new UnsupportedOperationException("values not supported yet!");
    }

    @Override
    public Set.Concurrent<? extends Entry<K, V>> entries() {
        throw new UnsupportedOperationException("entries not supported yet!");
    }

}
