package net.ollie.coljate.maps;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
public interface MapEntry<K, V> {

    K key();

    @CheckForNull
    V value();

}
