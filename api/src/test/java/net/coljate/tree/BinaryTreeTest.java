package net.coljate.tree;

/**
 *
 * @author ollie
 */
public interface BinaryTreeTest<K, V> extends TreeTest<K, V> {

    @Override
    BinaryTree<K, V, ?> createTestCollection();

    interface ZeroNodeTests<K, V> extends BinaryTreeTest<K, V>, TreeTest.ZeroNodeTests<K, V> {

    }

    interface OneNodeTests<K, V> extends BinaryTreeTest<K, V>, TreeTest.OneNodeTests<K, V> {

    }

}
