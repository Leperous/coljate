package net.ollie.coljate.maps;

/**
 *
 * @author Ollie
 */
public interface MutableMapEntry<K, V> extends MapEntry<K, V> {

    void setValue(V value);

}
