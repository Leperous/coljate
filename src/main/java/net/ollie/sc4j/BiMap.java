package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Mapping.Bijective;

/**
 *
 * @author Ollie
 */
public interface BiMap<K, V>
        extends Map<K, V>, Bijective<K, V> {

    @Override
    Set<V> values();

    @Override
    BiMap<V, K> inverse();

    @Override
    default boolean contains(final Object object) {
        return this.keys().contains(object) || this.values().contains(object);
    }

    @Override
    BiMap.Mutable<K, V> mutableCopy();

    @Override
    BiMap.Immutable<K, V> immutableCopy();

    interface Mutable<K, V>
            extends BiMap<K, V>, Map.Mutable<K, V> {

        @Override
        V put(K key, V value) throws DuplicateValueException;

    }

    interface Immutable<K, V>
            extends BiMap<K, V>, Map.Immutable<K, V> {

        @Override
        Set.Immutable<V> values();

        @Override
        BiMap.Immutable<V, K> inverse();

        @Override
        BiMap.Immutable<K, V> with(K key, V value) throws DuplicateValueException;

        @Override
        default BiMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

    class DuplicateValueException extends IllegalArgumentException {

        private static final long serialVersionUID = 1L;

    }

}
