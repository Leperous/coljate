package net.coljate.map.lazy;

import net.coljate.collection.lazy.LazyCollection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;

/**
 *
 * @author Ollie
 */
public interface LazyMap<K, V> extends LazyCollection<Entry<K, V>>, Map<K, V> {

    @Override
    default Map<K, V> evaluate() {
        return this.immutableCopy();
    }

    @Override
    default MutableMap<K, V> mutableCopy() {
        return Map.super.mutableCopy();
    }

    @Override
    default ImmutableMap<K, V> immutableCopy() {
        return Map.super.immutableCopy();
    }

}
