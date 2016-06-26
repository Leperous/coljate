package net.ollie.coljate.map;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface MutableKeyValue<K, @Nullable V> extends KeyValue<K, V> {

    /**
     *
     * @param value
     * @return the previous value.
     */
    V setValue(V value);

}
