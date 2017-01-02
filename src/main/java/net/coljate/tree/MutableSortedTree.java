package net.coljate.tree;

import net.coljate.map.MutableSortedMap;

/**
 *
 * @author Ollie
 */
public interface MutableSortedTree<K, V, N extends MutableNode<K, V>>
        extends MutableTree<K, V, N>, SortedTree<K, V, N>, MutableSortedMap<K, V> {

    @Override
    MutableSortedTree<K, V, ?> mutableCopy();

}
