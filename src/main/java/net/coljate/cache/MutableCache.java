package net.coljate.cache;

import net.coljate.map.MutableMap;

/**
 *
 * @author ollie
 */
public interface MutableCache<K, V> extends Cache<K, V>, MutableMap<K, V> {
    
}
