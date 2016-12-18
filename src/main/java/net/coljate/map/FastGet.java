package net.coljate.map;

import net.coljate.map.Associative;
import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface FastGet<K, V> extends Associative<K, V> {

    @Override
    @TimeComplexity(value = Complexity.CONSTANT, worstCase = Complexity.LINEAR)
    default V get(final K key) {
        return Associative.super.get(key);
    }

}
