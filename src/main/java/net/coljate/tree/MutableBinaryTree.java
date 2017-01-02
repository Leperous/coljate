package net.coljate.tree;

import net.coljate.tree.MutableBinaryTree.MutableBinaryNode;

/**
 * Mutable binary tree.
 *
 * @author ollie
 */
public interface MutableBinaryTree<K, V, B extends MutableBinaryNode<K, V>>
        extends BinaryTree<K, V, B>, MutableTree<K, V, B> {

    /**
     * Mutable node that allows updating of the value.
     *
     * @param <K>
     * @param <V>
     */
    interface MutableBinaryNode<K, V>
            extends BinaryNode<K, V>, MutableNode<K, V> {

    }

}
