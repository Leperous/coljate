package net.ollie.coljate.maps;

import java.util.function.Function;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Cached;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

/**
 *
 * @author Ollie
 */
public abstract class DelegatedCache<K, V>
        implements CacheBuilder<K, V> {

    private final Cache<K, V> delegate;

    protected DelegatedCache(final Cache<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public V get(final K key) {
        this.onRead(key);
        return delegate.get(key);
    }

    @Override
    public V getIfPresent(final K key) {
        this.onRead(key);
        return delegate.getIfPresent(key);
    }

    @Override
    public V put(K key, V value) {
        this.onWrite(key, value);
        return delegate.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        this.onRemove(key);
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Set<K> keys() {
        return delegate.keys();
    }

    @Override
    public Streamable<V> values() {
        return delegate.values();
    }

    protected abstract void onWrite(K key, V value);

    protected abstract void onRead(K key);

    protected abstract void onRemove(Object key);

    @Override
    public NonNegativeInteger count() {
        return this.keys().count();
    }

    @Override
    public <V2> Cache<K, V2> recompute(final Function<? super V, ? extends V2> function) {
        return delegate.recompute(function);
    }

    @Override
    public Cached.Mutable<K, V> mutableCopy() {
        return delegate.mutableCopy();
    }

    @Override
    public Map.Immutable<K, V> immutableCopy() {
        return delegate.immutableCopy();
    }

}
