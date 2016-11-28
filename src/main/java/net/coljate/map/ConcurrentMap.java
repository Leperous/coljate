package net.coljate.map;

import net.coljate.ConcurrentCollection;

/**
 *
 * @author ollie
 */
public interface ConcurrentMap<K, V> extends MutableMap<K, V>, ConcurrentCollection<Entry<K, V>> {

}
