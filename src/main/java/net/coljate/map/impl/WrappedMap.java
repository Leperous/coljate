package net.coljate.map.impl;

import java.util.Iterator;

import net.coljate.Collection;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.map.ImmutableMap.ImmutableEntry;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class WrappedMap<K, V> implements Map<K, V> {

    private final java.util.Map<K, V> delegate;
    private Set<K> keys;
    private Collection<V> values;

    protected WrappedMap(final java.util.Map<K, V> delegate) {
        this.delegate = delegate;
    }

    protected java.util.Map<K, V> mutableDelegateCopy() {
        return new java.util.HashMap<>(delegate);
    }

    @Override
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Entry<K, V> entry(final Object key) {
        final V value = this.get(key);
        return value != null || this.containsKey(key)
                ? new ImmutableEntry<>((K) key, value)
                : null;
    }

    @Override
    public Set<K> keys() {
        return keys == null
                ? (keys = Set.viewOf(delegate.keySet()))
                : keys;
    }

    @Override
    public Collection<V> values() {
        return values == null
                ? (values = Collection.viewOf(delegate.values()))
                : values;
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        return new MutableWrappedMap<>(this.mutableDelegateCopy());
    }

    @Override
    public ImmutableMap<K, V> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}