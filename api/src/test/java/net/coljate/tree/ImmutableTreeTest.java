package net.coljate.tree;

import net.coljate.map.ImmutableMapTest;

/**
 *
 * @author ollie
 */
public interface ImmutableTreeTest<K, V> extends TreeTest<K, V>, ImmutableMapTest<K, V> {

    @Override
    ImmutableTree<K, V, ?> createTestCollection();

    interface OneNodeTests<K, V>
            extends ImmutableTreeTest<K, V>, TreeTest.OneNodeTests<K, V>, ImmutableMapTest.OneEntryTests<K, V> {

    }

}
