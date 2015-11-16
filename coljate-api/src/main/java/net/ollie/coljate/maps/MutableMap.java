package net.ollie.coljate.maps;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.MutableCollection;
import net.ollie.coljate.sets.MutableSet;

/**
 *
 * @author Ollie
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableCollection<MapEntry<K, V>> {

    @Override
    MutableSet<K> keys();

    @NonNull
    MutableSet<? extends MutableMapEntry<K, V>> entries();

}
