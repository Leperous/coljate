package net.coljate.cache;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface SetMultimap<K, V> extends Multimap<K, V> {

    @Override
    Set<V> get(Object key);

}
