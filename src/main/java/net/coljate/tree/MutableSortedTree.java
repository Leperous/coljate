package net.coljate.tree;

import net.coljate.map.MutableEntry;
import net.coljate.map.MutableSortedMap;

/**
 *
 * @author Ollie
 */
public interface MutableSortedTree<K, V, E extends MutableEntry<K, V>>
        extends MutableTree<K, V, E>, SortedTree<K, V, E>, MutableSortedMap<K, V> {

    @Override
    MutableSortedTree<K, V, ?> mutableCopy();

}
