package net.coljate.map;

import java.util.Objects;

import net.coljate.map.impl.SimpleImmutableEntry;

/**
 *
 * @author ollie
 * @see Map
 */
public interface Entry<K, V> {

    K key();

    V value();

    default boolean contains(final Object key, final Object value) {
        return Objects.equals(key, this.key()) && Objects.equals(value, this.value());
    }

    default ImmutableEntry<K, V> immutableCopy() {
        return new SimpleImmutableEntry<>(this.key(), this.value());
    }

    default Entry<V, K> inverse() {
        return of(this.value(), this.key());
    }

    static <K, V> Entry<K, V> of(final K key, final V value) {
        return ImmutableEntry.of(key, value);
    }

}
