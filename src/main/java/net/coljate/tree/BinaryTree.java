package net.coljate.tree;

/**
 *
 * @author ollie
 */
public interface BinaryTree<K, V, N extends BinaryNode<K, V, N>>
        extends Tree<K, V, N> {

    @Override
    MutableBinaryTree<K, V, ?> mutableCopy();

    @Override
    ImmutableBinaryTree<K, V, ?> immutableCopy();

}
