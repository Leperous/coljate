package net.coljate.map;

import net.coljate.ImmutableCollection;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface ImmutableMap<K, V> extends Map<K, V>, ImmutableCollection<Map.Entry<K, V>> {

    @Override
    ImmutableSet<K> keys();

    @Override
    ImmutableCollection<V> values();

    @Override
    @Deprecated
    default ImmutableMap<K, V> immutableCopy() {
        return this;
    }

    class ImmutableEntry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private final V value;

        public ImmutableEntry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

    }

}
