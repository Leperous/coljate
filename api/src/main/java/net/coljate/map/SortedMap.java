package net.coljate.map;

import net.coljate.set.SortedSet;
import net.coljate.set.SequentialSet;

/**
 *
 * @author ollie
 */
public interface SortedMap<K, V> extends SortedSet<Entry<K, V>>, Map<K, V> {

    @Override
    SequentialSet<K> keys();

    @Override
    MutableSortedMap<K, V> mutableCopy();

}
