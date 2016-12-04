package net.coljate.map;

import net.coljate.collection.ConcurrentCollection;
import net.coljate.map.impl.ConcurrentWrappedHashMap;

/**
 *
 * @author ollie
 * @see java.util.concurrent.ConcurrentMap
 */
public interface ConcurrentMap<K, V>
        extends MutableMap<K, V>, ConcurrentCollection<Entry<K, V>> {

    @Override
    ConcurrentMap<K, V> mutableCopy();

    static <K, V> ConcurrentMap<K, V> createHashMap(final int initialCapacity) {
        return ConcurrentWrappedHashMap.create(initialCapacity);
    }

}
