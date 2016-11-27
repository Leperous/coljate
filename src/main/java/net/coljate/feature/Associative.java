package net.coljate.feature;

import java.util.Optional;
import java.util.function.Supplier;

/**
 *
 * @author ollie
 */
public interface Associative<K, V> {

    V get(K key);

    default Optional<V> maybeGet(final K key) {
        return Optional.ofNullable(this.get(key));
    }

    default V getOrDefault(final K key, final Supplier<? extends V> supplier) {
        final V got = this.get(key);
        return got == null ? supplier.get() : got;
    }

}
