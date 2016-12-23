package net.coljate.map;

import net.coljate.map.impl.SimpleImmutableEntry;

/**
 *
 * @author ollie
 * @see Map
 */
public interface Entry<K, V> {

    K key();

    V value();

    default ImmutableEntry<K, V> immutableCopy() {
        return new SimpleImmutableEntry<>(this.key(), this.value());
    }

}
