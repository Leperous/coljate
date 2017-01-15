package net.coljate.tree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.map.SortedMap;
import net.coljate.set.AbstractSet;
import net.coljate.set.OrderedSet;
import net.coljate.tree.impl.RedBlackTree;
import net.coljate.tree.navigation.TreeNavigation;
import net.coljate.util.Functions;
import net.coljate.util.iterator.Iterators;

/**
 *
 * @author Ollie
 */
public interface SortedTreeMap<K, V, N extends Node<K, V, N>>
        extends TreeMap<K, V, N>, SortedMap<K, V> {

    @Override
    N least();

    @Override
    N greatest();

    @Override
    default N first() {
        return this.least();
    }

    @Override
    default OrderedSet<K> keys() {
        return this.keys(TreeNavigation.getDefault());
    }

    @Override
    default OrderedSet<K> keys(final TreeNavigation navigation) {
        return new SortedTreeKeySet<>(this, navigation);
    }

    @Override
    MutableSortedTreeMap<K, V, ?> mutableCopy();

    static <K, V> MutableSortedTreeMap<K, V, ?> create(@Nonnull final Comparator<? super Entry<K, V>> comparator) {
        return new RedBlackTree<>(comparator);
    }

    static <K extends Comparable<? super K>, V> MutableSortedTreeMap<K, V, ?> createComparingKeys() {
        return RedBlackTree.keyComparing();
    }

    static <K, V> MutableSortedTreeMap<K, V, ?> createComparingKeys(@Nonnull final Comparator<? super K> keyComparator) {
        return RedBlackTree.keyComparing(keyComparator);
    }

    static <K, V> MutableSortedTreeMap<K, V, ?> copyOf(final SortedMap<K, V> map) {
        return RedBlackTree.copyOf(map);
    }

    static <K, V> MutableSortedTreeMap<K, V, ?> copyOf(final Map<? extends K, ? extends V> map, final Comparator<? super Entry<K, V>> comparator) {
        return RedBlackTree.copyOf(map, comparator);
    }

    class SortedTreeKeySet<K, N extends Node<K, ?, N>>
            extends AbstractSet<K>
            implements OrderedSet<K> {

        private final SortedTreeMap<K, ?, N> tree;
        private final TreeNavigation navigation;

        protected SortedTreeKeySet(final SortedTreeMap<K, ?, N> tree, final TreeNavigation navigation) {
            this.tree = Objects.requireNonNull(tree);
            this.navigation = Objects.requireNonNull(navigation);
        }

        @Override
        public boolean contains(final Object object) {
            return tree.containsKey(object);
        }

        @Override
        public Iterator<K> iterator() {
            return Iterators.transform(tree.iterator(navigation), N::key);
        }

        @Override
        public K first() {
            return Functions.ifNonNull(tree.first(), N::key);
        }

    }

}
