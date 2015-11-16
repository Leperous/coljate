package net.ollie.coljate.maps;

import net.ollie.coljate.sets.MutableSet;

/**
 *
 * @author Ollie
 */
public interface MutableMap<K, V> extends Map<K, V> {

    @Override
    MutableSet<K> keys();

    @Override
    MutableSet<? extends MutableMapEntry<K, V>> entries();

}
