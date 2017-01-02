package net.coljate.tree;

import net.coljate.map.ImmutableEntry;
import net.coljate.tree.ImmutableBinaryTree.ImmutableBinaryNode;

/**
 *
 * @author ollie
 */
public interface ImmutableBinaryTree<K, V>
        extends BinaryTree<K, V, ImmutableBinaryNode<K, V>>, ImmutableTree<K, V, ImmutableBinaryNode<K, V>> {

    @Override
    @Deprecated
    default ImmutableBinaryTree<K, V> immutableCopy() {
        return this;
    }

    interface ImmutableBinaryNode<K, V>
            extends BinaryNode<K, V>, ImmutableEntry<K, V> {

        @Override
        @Deprecated
        default ImmutableBinaryNode<K, V> immutableCopy() {
            return this;
        }

    }

}
