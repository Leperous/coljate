package net.coljate.map.lazy;

import net.coljate.collection.Collection;
import net.coljate.set.Set;

/**
 * A map whose values are computed. Also known as a lazy map or loading cache.
 *
 * @author ollie
 */
public interface Cache<K, V> extends LazyMap<K, V> {

    /**
     * @return currently loaded keys.
     */
    @Override
    Set<K> keys();

    /**
     * @return currently loaded values.
     */
    @Override
    Collection<V> values();

}
