package net.coljate.map;

import net.coljate.collection.ImmutableCollection;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface ImmutableMap<K, V> extends Map<K, V>, ImmutableSet<Entry<K, V>> {

    @Override
    ImmutableSet<? extends K> keys();

    @Override
    ImmutableCollection<V> values();

    @Override
    default ImmutableMap<K, V> with(final Entry<K, V> entry) {
        return this.with(entry.key(), entry.value());
    }

    ImmutableMap<K, V> with(K key, V value);

    @Override
    @Deprecated
    default ImmutableMap<K, V> immutableCopy() {
        return this;
    }

}
