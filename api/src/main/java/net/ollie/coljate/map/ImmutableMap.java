package net.ollie.coljate.map;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.set.ImmutableSet;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 * @author Ollie
 */
public interface ImmutableMap<K, V>
        extends Map<K, V>, ImmutableCollection<KeyValue<K, V>> {

    @Override
    ImmutableSet<K> keys();

    @Override
    ImmutableCollection<V> values();

    @Override
    ImmutableMap<K, V> tail();

    @NonNull
    ImmutableMap<K, V> with(K key, V value);

    @Override
    default ImmutableMap<K, V> with(final KeyValue<K, V> entry) {
        return this.with(entry.key(), entry.value());
    }

    @NonNull
    ImmutableMap<K, V> without(Object key);

    @Override
    @Deprecated
    default ImmutableMap<K, V> immutableCopy() {
        return this;
    }

}
