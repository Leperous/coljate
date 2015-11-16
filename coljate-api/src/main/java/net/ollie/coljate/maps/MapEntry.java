package net.ollie.coljate.maps;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface MapEntry<K, @Nullable V> {

    K key();

    V value();

}
