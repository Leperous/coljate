package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Mapping.Bijective;

/**
 *
 * @author Ollie
 */
public interface BiMap<K, V>
        extends Map<K, V>, Bijective<K, V> {

    @Override
    Set<K> keys();

    @Override
    Set<V> values();

    @Override
    BiMap<V, K> inverse();

    @Override
    default boolean contains(final Object object) {
        return this.keys().contains(object) || this.values().contains(object);
    }

}
