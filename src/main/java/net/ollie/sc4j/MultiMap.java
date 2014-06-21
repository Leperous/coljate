package net.ollie.sc4j;

/**
 *
 * @author Ollie
 */
public interface MultiMap<K, V>
        extends Map<K, V> {

    @Override
    MultiMap.Mutable<K, V> mutableCopy();

    @Override
    MultiMap.Immutable<K, V> immutableCopy();

    interface Mutable<K, V>
            extends MultiMap<K, V>, Map.Mutable<K, V> {

    }

    interface Immutable<K, V>
            extends MultiMap<K, V>, Map.Immutable<K, V> {

        @Override
        MultiMap.Immutable<K, V> with(K key, V value);

        @Override
        default MultiMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
