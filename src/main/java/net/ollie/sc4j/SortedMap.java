package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Sorted;

/**
 *
 * @author Ollie
 */
public interface SortedMap<K, V>
        extends Map<K, V>, Sorted<V> {

    @Override
    SortedSet<K> keys();

    @Override
    List<V> values();

    @Override
    default V head() {
        return Sorted.super.head();
    }

    @Override
    SortedMap.Mutable<K, V> mutableCopy();

    @Override
    SortedMap.Immutable<K, V> immutableCopy();

    @Override
    default int hash() {
        return Map.super.hash();
    }

    interface Mutable<K, V>
            extends SortedMap<K, V>, Map.Mutable<K, V>, Sorted.Mutable<V> {

        @Override
        SortedSet.Mutable<K> keys();

        @Override
        List.Mutable<V> values();

    }

    interface Immutable<K, V>
            extends SortedMap<K, V>, Map.Immutable<K, V>, Sorted.Immutable<V> {

        @Override
        SortedSet.Immutable<K> keys();

        @Override
        List.Immutable<V> values();

        @Override
        default SortedMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
