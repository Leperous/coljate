package net.coljate.map.impl;

import java.util.Objects;

import net.coljate.collection.AllCollectionSizeTest;
import net.coljate.map.Entry;
import net.coljate.map.LoadingCacheTest;
import net.coljate.map.ObjectMapTest;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ConcurrentLoadingCacheTest
        extends ObjectMapTest
        implements LoadingCacheTest<Object, Object>, AllCollectionSizeTest<Entry<Object, Object>> {

    @Override
    public ConcurrentLoadingCache<Object, Object> create(final java.util.List<Entry<Object, Object>> entries) {
        final ConcurrentLoadingCache<Object, Object> cache = new ConcurrentLoadingCache<>(key -> entries.stream()
                .filter(entry -> Objects.equals(key, entry.key()))
                .map(Entry::value)
                .findAny()
                .orElse(null));
        entries.forEach(entry -> cache.get(entry.key()));
        return cache;
    }

}
