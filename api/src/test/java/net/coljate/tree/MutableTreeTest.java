package net.coljate.tree;

import net.coljate.map.MutableMapTest;

/**
 *
 * @author ollie
 */
public interface MutableTreeTest<K, V> extends TreeTest<K, V>, MutableMapTest<K, V> {

    @Override
    MutableTree<K, V, ?> createTestCollection();

    interface ZeroNodeTests<K, V>
            extends MutableTreeTest<K, V>, TreeTest.ZeroNodeTests<K, V>, MutableMapTest.ZeroEntryTests<K, V> {

    }

    interface OneNodeTests<K, V>
            extends MutableTreeTest<K, V>, TreeTest.OneNodeTests<K, V>, MutableMapTest.OneEntryTests<K, V> {

    }

}
