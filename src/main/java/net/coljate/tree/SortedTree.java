package net.coljate.tree;

import java.util.Iterator;
import java.util.Objects;

import net.coljate.map.SortedMap;
import net.coljate.set.AbstractSet;
import net.coljate.set.OrderedSet;
import net.coljate.tree.navigation.TreeNavigation;
import net.coljate.util.Functions;
import net.coljate.util.iterator.Iterators;

/**
 *
 * @author Ollie
 */
public interface SortedTree<K, V, N extends Node<K, V, N>>
        extends Tree<K, V, N>, SortedMap<K, V> {

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
    MutableSortedTree<K, V, ?> mutableCopy();

    class SortedTreeKeySet<K, N extends Node<K, ?, N>>
            extends AbstractSet<K>
            implements OrderedSet<K> {

        private final SortedTree<K, ?, N> tree;
        private final TreeNavigation navigation;

        protected SortedTreeKeySet(final SortedTree<K, ?, N> tree, final TreeNavigation navigation) {
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
