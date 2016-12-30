package net.coljate.map;

import net.coljate.set.SortedSet;

/**
 *
 * @author ollie
 */
public interface SortedMap<K, V> extends SortedSet<Entry<K, V>>, Map<K, V> {

    @Override
    SortedSet<K> keys();

    @Override
    MutableSortedMap<K, V> mutableCopy();

}
