package net.coljate.feature;

import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface FastGet<K, V> extends Associative<K, V> {

    @Override
    @TimeComplexity(value = Complexity.CONSTANT, worstCase = Complexity.LINEAR)
    V get(K key);

}
