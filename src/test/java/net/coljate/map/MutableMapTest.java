package net.coljate.map;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author ollie
 */
public interface MutableMapTest<K, V> extends MapTest<K, V>, MutableCollectionTest<Entry<K, V>> {

    @Override
    MutableMap<K, V> createTestCollection();

    interface ZeroElementTests<K, V> extends MutableMapTest<K, V>, MapTest.ZeroElementTests<K, V>, MutableCollectionTest.ZeroElementTests<Entry<K, V>> {

    }

}
