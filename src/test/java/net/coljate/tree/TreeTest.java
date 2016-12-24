package net.coljate.tree;

import net.coljate.map.MapTest;

/**
 *
 * @author ollie
 */
public interface TreeTest<K, V> extends MapTest<K, V> {

    @Override
    Tree<K, V, ?> createTestCollection();

    interface ZeroEntryTests<K, V> extends TreeTest<K, V>, MapTest.ZeroEntryTests<K, V> {

    }

    interface OneEntryTests<K, V> extends TreeTest<K, V>, MapTest.OneEntryTests<K, V> {

    }

}
