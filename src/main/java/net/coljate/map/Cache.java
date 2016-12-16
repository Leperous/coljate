package net.coljate.map;

import net.coljate.feature.Associative;

/**
 *
 * @author ollie
 */
public interface Cache<K, V> extends Associative<K, V> {

    V evict(Object key);

    @Deprecated
    boolean contains(Object object);

    MutableMap<K, V> mutableCopy();

}
