package net.coljate.map;

import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.coljate.Container;
import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> extends Container {

    @TimeComplexity(bestCase = Complexity.CONSTANT)
    @CheckForNull
    default V get(final K key) {
        return this.getIfPresent(key);
    }

    @CheckForNull
    V getIfPresent(Object key);

    @Nonnull
    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

}
