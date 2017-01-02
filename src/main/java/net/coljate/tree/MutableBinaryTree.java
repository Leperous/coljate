package net.coljate.tree;

import net.coljate.map.MutableEntry;
import net.coljate.tree.MutableBinaryTree.MutableBinaryNode;

/**
 * Mutable binary tree.
 *
 * @author ollie
 */
public interface MutableBinaryTree<K, V>
        extends BinaryTree<K, V, MutableBinaryNode<K, V>>, MutableTree<K, V, MutableBinaryNode<K, V>> {

    /**
     * Mutable node that allows updating of the value.
     *
     * @param <K>
     * @param <V>
     */
    interface MutableBinaryNode<K, V>
            extends BinaryNode<K, V>, MutableEntry<K, V> {

    }

}
