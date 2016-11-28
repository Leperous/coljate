package net.coljate.feature;

import java.util.Optional;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> {

    V get(K key);

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

}
