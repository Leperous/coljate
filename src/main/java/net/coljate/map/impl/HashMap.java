package net.coljate.map.impl;

import net.coljate.feature.FastGet;
import net.coljate.map.Map;

/**
 *
 * @author ollie
 */
public interface HashMap<K, V> extends Map<K, V>, FastGet<K, V> {

    @Override
    default java.util.HashMap<K, V> javaMapCopy() {
        return this.javaMapCopy(java.util.HashMap::new);
    }

    @Override
    default V get(final K key) {
        return Map.super.get(key);
    }

}
