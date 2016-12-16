package net.coljate.feature;

import java.util.Optional;

import net.coljate.Container;
import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> extends Container {

    @TimeComplexity(Complexity.UNKNOWN)
    V get(K key);
    
    default V getIfPresent(final Object key) {
        return this.contains(key) ? this.get((K)key) : null;
    }

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

}
