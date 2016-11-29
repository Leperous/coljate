package net.coljate.feature;

/**
 *
 * @author ollie
 */
public interface FastGet<K, V> extends Associative<K, V> {

    @Override
    V get(K key);

}
