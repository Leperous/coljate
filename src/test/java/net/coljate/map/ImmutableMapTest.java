package net.coljate.map;

import net.coljate.collection.ImmutableCollectionTest;

/**
 *
 * @author Ollie
 */
public interface ImmutableMapTest<K, V> extends MapTest<K, V>, ImmutableCollectionTest<Entry<K, V>> {

    @Override
    ImmutableMap<K, V> create(Entry<K, V>... elements);

}
