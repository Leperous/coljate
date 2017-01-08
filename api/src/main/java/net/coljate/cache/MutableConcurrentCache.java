package net.coljate.cache;

import net.coljate.map.ConcurrentMap;

/**
 *
 * @author ollie
 */
public interface MutableConcurrentCache<K, V> extends MutableCache<K, V>, ConcurrentMap<K, V> {

}
