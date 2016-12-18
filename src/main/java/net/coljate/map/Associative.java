package net.coljate.map;

import java.util.Optional;

import net.coljate.Container;
import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> extends Container {

    @TimeComplexity(bestCase = Complexity.CONSTANT)
    default V get(K key) {
        return this.getIfPresent(key);
    }

    V getIfPresent(Object key);

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

}
