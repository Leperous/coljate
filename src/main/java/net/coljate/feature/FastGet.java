package net.coljate.feature;

import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface FastGet<K, V> extends Associative<K, V> {

    @Override
    @TimeComplexity(Complexity.CONSTANT)
    V get(K key);

}
