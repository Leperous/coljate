package net.coljate.map;

import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

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
