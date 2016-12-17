package net.coljate.map;

import net.coljate.collection.Collection;
import net.coljate.set.Set;

/**
 * A map whose values are computed. Also known as a lazy map.
 *
 * @author ollie
 */
public interface LoadingCache<K, V> extends Map<K, V> {

    /**
     * @return currently loaded keys.
     */
    @Override
    Set<? extends K> keys();

    /**
     * @return currently loaded values.
     */
    @Override
    Collection<V> values();

}
