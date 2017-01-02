package net.coljate.tree;

import net.coljate.set.ImmutableSet;
import net.coljate.tree.ImmutableBinaryTree.ImmutableBinaryNode;

/**
 *
 * @author ollie
 */
public interface ImmutableBinaryTree<K, V, B extends ImmutableBinaryNode<K, V>>
        extends BinaryTree<K, V, B>, ImmutableTree<K, V, B> {

    @Override
    @Deprecated
    default ImmutableBinaryTree<K, V, B> immutableCopy() {
        return this;
    }

    interface ImmutableBinaryNode<K, V>
            extends BinaryNode<K, V>, ImmutableNode<K, V> {

        @Override
        ImmutableBinaryNode<K, V> left();

        @Override
        ImmutableBinaryNode<K, V> right();

        @Override
        public default ImmutableSet<? extends ImmutableNode<K, V>> children() {
            return ImmutableSet.ofNonNull(this.left(), this.right());
        }

        @Override
        @Deprecated
        default ImmutableBinaryNode<K, V> immutableCopy() {
            return this;
        }

    }

}
