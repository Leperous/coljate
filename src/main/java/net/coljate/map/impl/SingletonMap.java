package net.coljate.map.impl;

import java.io.Serializable;
import java.util.Objects;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.ImmutableCollection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class SingletonMap<K, V>
        extends ImmutableEntry<K, V>
        implements ImmutableMap<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <K, V> SingletonMap<K, V> of(final K key, final V value) {
        return new SingletonMap<>(key, value);
    }

    private transient ImmutableSet<K> keys;
    private transient ImmutableCollection<V> values;

    protected SingletonMap(final K key, final V value) {
        super(key, value);
    }

    @Override
    public ImmutableSet<K> keys() {
        return keys == null
                ? (keys = ImmutableSet.of(this.key()))
                : keys;
    }

    @Override
    public ImmutableCollection<V> values() {
        return values == null
                ? (values = ImmutableCollection.of(this.value()))
                : values;
    }

    @Override
    public SingletonMap<K, V> entry(final Object key) {
        return Objects.equals(this.key(), key)
                ? this
                : null;
    }

    @Override
    public ImmutableMap<K, V> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return UnmodifiableIterator.of(this);
    }

    @Deprecated
    @Override
    public SingletonMap<K, V> immutableCopy() {
        return this;
    }

}
