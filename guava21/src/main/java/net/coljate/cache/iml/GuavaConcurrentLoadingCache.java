package net.coljate.cache.iml;

import com.google.common.cache.LoadingCache;

import net.coljate.cache.impl.GuavaLoadingCache;
import net.coljate.map.ConcurrentMap;
import net.coljate.cache.MutableConcurrentCache;

/**
 *
 * @author Ollie
 */
public class GuavaConcurrentLoadingCache<K, V>
        extends GuavaLoadingCache<K, V>
        implements MutableConcurrentCache<K, V> {

    protected GuavaConcurrentLoadingCache(final LoadingCache<K, V> cache) {
        super(cache);
    }

    @Override
    public ConcurrentMap<K, V> mutableCopy() {
        return MutableConcurrentCache.super.mutableCopy();
    }

}
