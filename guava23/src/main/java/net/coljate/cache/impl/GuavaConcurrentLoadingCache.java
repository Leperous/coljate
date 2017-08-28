package net.coljate.cache.impl;

import com.google.common.cache.LoadingCache;

import net.coljate.cache.ConcurrentCache;
import net.coljate.map.ConcurrentMap;

/**
 *
 * @author Ollie
 */
public class GuavaConcurrentLoadingCache<K, V>
        extends GuavaLoadingCache<K, V>
        implements ConcurrentCache<K, V> {

    protected GuavaConcurrentLoadingCache(final LoadingCache<K, V> cache) {
        super(cache);
    }

    @Override
    public ConcurrentMap<K, V> mutableCopy() {
        return ConcurrentCache.super.mutableCopy();
    }

}
