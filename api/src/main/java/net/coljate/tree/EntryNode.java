package net.coljate.tree;

import net.coljate.map.Entry;

/**
 *
 * @author ollie
 */
public interface EntryNode<K, V, N extends EntryNode<K, V, N>>
        extends Entry<K, V>, Node<N> {

}
