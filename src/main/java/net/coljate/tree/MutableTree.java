package net.coljate.tree;

import net.coljate.collection.Collection;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;

/**
 *
 * @author ollie
 */
public interface MutableTree<K, V, E extends MutableEntry<K, V>> extends Tree<K, V, E>, MutableMap<K, V> {

    @Override
    default E entry(final Object key) {
        return Tree.super.entry(key);
    }

    @Override
    E root();

    @Override
    Collection<? extends MutableTree<K, V, E>> subtrees(Object key);

}
