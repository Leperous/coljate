package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.imposed.sorting.Sorted;
import net.ollie.sc4j.utils.Functions;

/**
 *
 * @author Ollie
 */
public interface TreeMap<K, V>
        extends Tree<K, V>, Map<K, V>, Sorted<K> {

    @Override
    Set<K> children(K key);

    @Override
    <V2> TreeMap<K, V2> mapValues(Function<? super V, ? extends V2> function);

    @Override
    default TreeMap<K, V> filterValues(final Predicate<? super V> predicate) {
        return this.mapValues(Functions.satisfying(predicate));
    }

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
        <V2> TreeMap.Immutable<K, V2> mapValues(Function<? super V, ? extends V2> function);

        @Override
        default TreeMap.Immutable<K, V> filter(final Predicate<? super V> predicate) {
            return this.filterValues(predicate);
        }

        @Override
        default TreeMap.Immutable<K, V> filterValues(final Predicate<? super V> predicate) {
            return this.mapValues(Functions.satisfying(predicate));
        }

        @Override
        TreeMap.Immutable<K, V> tail();

        @Override
        default TreeMap.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
