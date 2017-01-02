package net.coljate.tree;

import net.coljate.map.SortedMap;
import net.coljate.set.SortedSet;
import net.coljate.tree.navigation.TreeNavigation;

/**
 *
 * @author Ollie
 */
public interface SortedTree<K, V, N extends Node<K, V, N>>
        extends Tree<K, V, N>, SortedMap<K, V> {

    @Override
    default SortedSet<K> keys() {
        return this.keys(TreeNavigation.getDefault());
    }

    @Override
    default SortedSet<K> keys(final TreeNavigation navigation) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    MutableSortedTree<K, V, ?> mutableCopy();

}
