package net.coljate.tree;

/**
 *
 * @author ollie
 */
public interface BinaryTree<K, V, B extends BinaryNode<K, V>>
        extends Tree<K, V, B> {

    @Override
    MutableBinaryTree<K, V> mutableCopy();

    @Override
    ImmutableBinaryTree<K, V> immutableCopy();

}
