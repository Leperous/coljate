package net.coljate.tree;

import net.coljate.map.Entry;
import net.coljate.map.SortedMap;
import net.coljate.set.SortedSet;
import net.coljate.tree.navigation.TreeNavigation;

/**
 *
 * @author Ollie
 */
public interface SortedTree<K, V, E extends Entry<K, V>>
        extends Tree<K, V, E>, SortedMap<K, V> {

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
