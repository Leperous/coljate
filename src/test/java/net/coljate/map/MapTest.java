package net.coljate.map;

import net.coljate.collection.CollectionTest;

/**
 *
 * @author Ollie
 */
public interface MapTest<K, V> extends CollectionTest<Entry<K, V>> {

    @Override
    Map<K, V> create(java.util.List<Entry<K, V>> entries);

}
