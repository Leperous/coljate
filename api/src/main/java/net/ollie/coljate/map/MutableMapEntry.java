package net.ollie.coljate.map;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface MutableMapEntry<K, @Nullable V> extends MapEntry<K, V> {

    /**
     *
     * @param value
     * @return the previous value.
     */
    V setValue(V value);

}
