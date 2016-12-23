package net.coljate.map;

import net.coljate.collection.SortedCollection;
import net.coljate.set.SortedSet;

/**
 *
 * @author ollie
 */
public interface SortedMap<K, V> extends SortedCollection<Entry<K, V>>, Map<K, V> {

    @Override
    SortedSet<K> keys();

}
