package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public interface MutableMapEntry<K, V> extends MapEntry<K, V> {

    void setValue(V value);

}
