package net.coljate.tree;

/**
 *
 * @author ollie
 */
public interface BinaryTreeMap<K, V, N extends BinaryNode<K, V, N>>
        extends TreeMap<K, V, N> {

    @Override
    MutableBinaryTreeMap<K, V, ?> mutableCopy();

    @Override
    ImmutableBinaryTreeMap<K, V, ?> immutableCopy();

}