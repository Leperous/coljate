package net.coljate.cache;

import net.coljate.collection.Collection;
import net.coljate.collection.Empty;
import net.coljate.set.Set;
import net.coljate.set.impl.EmptySet;

/**
 *
 * @author ollie
 */
public interface EmptyCache<K, V> extends Cache<K, V> {

    @Override
    default EmptySet<K> keys() {
        return Set.of();
    }

    @Override
    default Empty<V> values() {
        return Collection.of();
    }

}
