package net.coljate.tree;

import net.coljate.collection.Collection;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;

/**
 *
 * @author ollie
 */
public interface MutableTree<K, V> extends Tree<K, V>, MutableMap<K, V> {

    @Override
    default MutableEntry<K, V> entry(final Object key) {
        return (MutableEntry<K, V>) Tree.super.entry(key);
    }

    @Override
    MutableEntry<K, V> root();

    @Override
    Collection<? extends MutableTree<K, V>> subtrees(Object key);

}
