package net.coljate.cache.policy;

import net.coljate.cache.MutableCache;
import net.coljate.collection.Collection;
import net.coljate.map.MutableEntry;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class EvictingMutableCache<K, V> implements MutableCache<K, V> {

    private final MutableCache<K, V> cache;
    private final CacheEvictionPolicy evictionPolicy;

    public EvictingMutableCache(final MutableCache<K, V> cache, final CacheEvictionPolicy evictionPolicy) {
        this.cache = cache;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public Set<K> keys() {
        return cache.keys();
    }

    @Override
    public Collection<V> values() {
        return cache.values();
    }

    @Override
    public MutableEntry<K, V> getEntry(Object key) {
        final MutableEntry<K, V> entry = cache.getEntry(key);
        if (entry != null) {
            evictionPolicy.notifyRead(key);
        }
        return entry;
    }

    @Override
    public V put(final K key, final V value) {
        final Object remove = evictionPolicy.notifyWrite(key);
        final V put = cache.put(key, value);
        if (remove != null) {
            this.evict(remove);
        }
        return put;
    }

    @Override
    public boolean remove(Object key, Object value) {
        final boolean removed = cache.remove(key, value);
        if (removed) {
            evictionPolicy.notifyRemove(key);
        }
        return removed;
    }

    @Override
    public V evict(final Object key) {
        evictionPolicy.notifyRemove(key);
        return cache.evict(key);
    }

    @Override
    public void clear() {
        cache.clear();
        evictionPolicy.notifyClear();
    }

    @Override
    public MutableCache<K, V> mutableCopy() {
        return cache.mutableCopy();
    }

}
