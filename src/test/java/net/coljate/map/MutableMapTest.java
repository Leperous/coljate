package net.coljate.map;

import net.coljate.set.MutableSetTest;

/**
 *
 * @author ollie
 */
public interface MutableMapTest<K, V> extends MapTest<K, V>, MutableSetTest<Entry<K, V>> {

    @Override
    MutableMap<K, V> createTestCollection();

    interface ZeroElementTests<K, V> extends MutableMapTest<K, V>, MapTest.ZeroElementTests<K, V>, MutableSetTest.ZeroElementTests<Entry<K, V>> {

    }

}
