package net.coljate.cache.impl;

import java.util.function.Function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import net.coljate.cache.MutableCache;
import net.coljate.collection.Collection;
import net.coljate.map.AbstractEntry;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("element-type-mismatch")
public class GuavaLoadingCache<K, V>
        implements MutableCache<K, V> {

    public static <K, V> GuavaLoadingCache<K, V> create(final Function<? super K, ? extends V> valueFunction) {
        return create(CacheBuilder.newBuilder(), valueFunction);
    }

    public static <K, V> GuavaLoadingCache<K, V> create(final CacheBuilder<? super K, ? super V> builder, final Function<? super K, ? extends V> valueFunction) {
        return new GuavaLoadingCache<>(builder.build(new CacheLoader<K, V>() {

            @Override
            public V load(final K key) throws Exception {
                return valueFunction.apply(key);
            }

        }));
    }

    private final LoadingCache<K, V> cache;
    private Set<K> keys;
    private Collection<V> values;

    protected GuavaLoadingCache(final LoadingCache<K, V> cache) {
        this.cache = cache;
    }

    @Override
    public boolean containsKey(final Object key) {
        return cache.asMap().containsKey(key);
    }

    @Override
    public V get(final K key) {
        return cache.getUnchecked(key);
    }

    @Override
    public V getIfPresent(final Object key) {
        return cache.getIfPresent(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public MutableEntry<K, V> getEntry(final Object key) {
        return cache.getIfPresent(key) != null
                ? new LoadingCacheEntry((K) key)
                : null;
    }

    @Override
    public V put(final K key, final V value) {
        return cache.asMap().put(key, value);
    }

    @Override
    public boolean remove(final Object key, final Object value) {
        return cache.asMap().remove(key, value);
    }

    @Override
    public boolean removeKey(final Object key) {
        return cache.asMap().remove(key) != null;
    }

    @Override
    public V evict(final Object key) {
        return cache.asMap().remove(key);
    }

    @Override
    public void clear() {
        cache.invalidateAll();
    }

    @Override
    public Set<K> keys() {
        return keys == null
                ? keys = Set.viewOf(cache.asMap().keySet())
                : keys;
    }

    @Override
    public Collection<V> values() {
        return values == null
                ? values = Collection.viewOf(cache.asMap().values())
                : values;
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        return MutableMap.copyIntoHashMap(cache.asMap());
    }

    private final class LoadingCacheEntry extends AbstractEntry<K, V> implements MutableEntry<K, V> {

        private final K key;

        LoadingCacheEntry(final K key) {
            this.key = key;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return GuavaLoadingCache.this.get(key);
        }

        @Override
        public void setValue(final V value) {
            GuavaLoadingCache.this.put(key, value);
        }

    }

}
