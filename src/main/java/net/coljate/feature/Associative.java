package net.coljate.feature;

import java.util.Optional;

import net.coljate.Container;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> extends Container {

    V get(K key);

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

}
