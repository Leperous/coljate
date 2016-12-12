package net.coljate.feature;

/**
 *
 * @author ollie
 */
public interface FastGet<K, V> extends Associative<K, V> {

    @Override
    @Complexity(Complexity.Order.CONSTANT)
    V get(K key);

}
