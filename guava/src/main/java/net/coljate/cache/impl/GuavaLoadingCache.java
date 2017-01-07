package net.coljate.cache.impl;

import java.util.function.Function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import net.coljate.cache.MutableCache;
import net.coljate.map.AbstractEntry;
import net.coljate.map.MutableEntry;
import net.coljate.map.impl.MutableWrappedMap;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("element-type-mismatch")
public class GuavaLoadingCache<K, V>
        extends MutableWrappedMap<K, V>
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

    protected GuavaLoadingCache(final LoadingCache<K, V> cache) {
        super(cache.asMap());
        this.cache = cache;
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
    public void clear() {
        cache.invalidateAll();
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
