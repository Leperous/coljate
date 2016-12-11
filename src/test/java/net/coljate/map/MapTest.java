package net.coljate.map;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author Ollie
 */
public interface MapTest<K, V> extends CollectionTest<Entry<K, V>> {

    @Override
    Map<K, V> create(Entry<K, V>... entries);

}
