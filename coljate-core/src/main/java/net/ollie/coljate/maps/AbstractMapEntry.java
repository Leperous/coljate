package net.ollie.coljate.maps;

import java.util.Objects;

import net.ollie.coljate.Map;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

    private final K key;

    protected AbstractMapEntry(final K key) {
        this.key = key;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public Map.Mutable.Entry<K, V> mutableCopy() {
        return MutableConcurrentMapEntry.copy(this);
    }

    @Override
    public Map.Immutable.Entry<K, V> immutableCopy() {
        return ImmutableMapEntry.copy(this);
    }

    @Override
    public String toString() {
        return this.key().toString() + '=' + this.value();
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof Map.Entry && this.equals((Map.Entry) that);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.key());
        hash = 79 * hash + Objects.hashCode(this.value());
        return hash;
    }

}
