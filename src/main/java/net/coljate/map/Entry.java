package net.coljate.map;

/**
 *
 * @author ollie
 */
public interface Entry<K, V> {

    K key();

    V value();

    default ImmutableEntry<K, V> immutableCopy() {
        return new ImmutableEntry<>(this.key(), this.value());
    }

}
