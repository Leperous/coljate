package net.coljate.tree;

import net.coljate.tree.MutableBinaryTree.MutableBinaryNode;

/**
 * Mutable binary tree.
 *
 * @author ollie
 */
public interface MutableBinaryTree<K, V, N extends MutableBinaryNode<K, V, N>>
        extends BinaryTree<K, V, N>, MutableTree<K, V, N> {

    /**
     * Mutable node that allows updating of the value.
     *
     * @param <K>
     * @param <V>
     */
    interface MutableBinaryNode<K, V, N extends MutableBinaryNode<K, V, N>>
            extends BinaryNode<K, V, N>, MutableNode<K, V, N> {

    }

}
