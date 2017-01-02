package net.coljate.tree;

import net.coljate.set.ImmutableSet;
import net.coljate.tree.ImmutableBinaryTree.ImmutableBinaryNode;

/**
 *
 * @author ollie
 */
public interface ImmutableBinaryTree<K, V, N extends ImmutableBinaryNode<K, V, N>>
        extends BinaryTree<K, V, N>, ImmutableTree<K, V, N> {

    @Override
    @Deprecated
    default ImmutableBinaryTree<K, V, N> immutableCopy() {
        return this;
    }

    interface ImmutableBinaryNode<K, V, N extends ImmutableBinaryNode<K, V, N>>
            extends BinaryNode<K, V, N>, ImmutableNode<K, V, N> {

        @Override
        public default ImmutableSet<? extends N> children() {
            return BinaryNode.super.children().immutableCopy();
        }

        @Override
        @Deprecated
        default ImmutableBinaryNode<K, V, N> immutableCopy() {
            return this;
        }

    }

}
