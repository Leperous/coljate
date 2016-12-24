package net.coljate.map.lazy;

import net.coljate.map.ConcurrentMap;

/**
 *
 * @author ollie
 */
public interface ConcurrentCache<K, V> extends MutableCache<K, V>, ConcurrentMap<K, V> {

    @Override
    default ConcurrentMap<K, V> mutableCopy() {
        return ConcurrentMap.super.mutableCopy();
    }

}
