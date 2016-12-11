package net.coljate.map;

import net.coljate.collection.ImmutableCollectionTest;

/**
 *
 * @author Ollie
 */
public interface ImmutableMapTest<K, V> extends MapTest<K, V>, ImmutableCollectionTest<Entry<K, V>> {

    @Override
    ImmutableMap<K, V> create(java.util.List<Entry<K, V>> elements);

}
