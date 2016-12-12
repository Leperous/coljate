package net.coljate.feature;

import net.coljate.feature.complexity.TimeComplexity;

import java.util.Optional;

import net.coljate.Container;
import net.coljate.feature.complexity.Complexity;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> extends Container {

    @TimeComplexity(Complexity.UNKNOWN)
    V get(K key);

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

}
