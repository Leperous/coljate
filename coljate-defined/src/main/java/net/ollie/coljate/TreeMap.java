package net.ollie.coljate;


import net.ollie.coljate.imposed.sorting.Sorted;

/**
 *
 * @author Ollie
 */
public interface TreeMap<K, V>
        extends Tree<K, V>, Map<K, V>, Sorted<K> {

    @Override
    Set<K> children(K key);

    @Override
    Set<? extends TreeMap<K, V>> subtrees(K key);

    @Override
    TreeMap.Mutable<K, V> mutableCopy();

    @Override
    TreeMap.Immutable<K, V> immutableCopy();

    @Override
    default boolean isEmpty() {
        return Map.super.isEmpty();
    }

    @Override
    default int hash() {
        return Map.super.hash();
    }

    interface Mutable<K, V>
            extends TreeMap<K, V>, Tree.Mutable<K, V>, Map.Mutable<K, V> {

        @Override
        default void delete(final K key) {
            this.remove(key);
        }

    }

    interface Immutable<K, V>
            extends TreeMap<K, V>, Tree.Immutable<K, V>, Map.Immutable<K, V> {

        @Override
        Set.Immutable<? extends TreeMap.Immutable<K, V>> subtrees(K key);

        @Override
        TreeMap.Immutable<K, V> tail();

        @Override
        default TreeMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
