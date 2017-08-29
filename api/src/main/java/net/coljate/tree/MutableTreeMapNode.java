package net.coljate.tree;

import net.coljate.map.MutableEntry;

/**
 *
 * @author ollie
 */
public interface MutableTreeMapNode<K, V, N extends MutableTreeMapNode<K, V, N>>
        extends TreeMapNode<K, V, N>, MutableEntry<K, V> {

}
