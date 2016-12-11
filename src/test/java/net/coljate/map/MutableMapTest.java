package net.coljate.map;

import net.coljate.collection.MutableCollectionTest;

/**
 *
 * @author Ollie
 */
public interface MutableMapTest<K, V> extends MapTest<K, V>, MutableCollectionTest<Entry<K, V>> {

    @Override
    MutableMap<K, V> create(Entry<K, V>... entries);

}
