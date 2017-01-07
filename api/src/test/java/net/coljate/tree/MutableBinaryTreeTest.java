package net.coljate.tree;

/**
 *
 * @author ollie
 */
public interface MutableBinaryTreeTest<K, V>
        extends BinaryTreeTest<K, V>, MutableTreeTest<K, V> {

    @Override
    MutableBinaryTree<K, V, ?> createTestCollection();

    interface ZeroNodeTests<K, V>
            extends MutableBinaryTreeTest<K, V>, BinaryTreeTest.ZeroNodeTests<K, V>, MutableTreeTest.ZeroNodeTests<K, V> {

    }

    interface OneNodeTests<K, V>
            extends MutableBinaryTreeTest<K, V>, BinaryTreeTest.OneNodeTests<K, V>, MutableTreeTest.OneNodeTests<K, V> {

    }

}
