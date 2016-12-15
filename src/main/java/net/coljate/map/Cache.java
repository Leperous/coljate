package net.coljate.map;

import net.coljate.feature.Associative;

/**
 *
 * @author ollie
 */
public interface Cache<K, V> extends Associative<K, V> {

    /**
     *
     * @param key
     * @return the current value, if any, that is associated with the key. If
     * the key is currently being computed this method will not wait.
     */
    V getIfPresent(K key);

    V evict(Object key);

    @Deprecated
    boolean contains(Object object);

    MutableMap<K, V> mutableCopy();

}
