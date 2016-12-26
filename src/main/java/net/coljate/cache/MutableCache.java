package net.coljate.cache;

import java.util.function.Function;

import net.coljate.cache.impl.ConcurrentMutableMapBackedCache;
import net.coljate.map.MutableMap;

/**
 *
 * @author ollie
 */
public interface MutableCache<K, V> extends Cache<K, V>, MutableMap<K, V> {

    static <K, V> MutableCache<K, V> create(final Function<? super K, ? extends V> valueFunction) {
        return ConcurrentMutableMapBackedCache.create(valueFunction);
    }

}
