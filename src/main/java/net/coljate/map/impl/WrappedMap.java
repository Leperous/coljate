package net.coljate.map.impl;

import java.util.Iterator;
import java.util.Objects;

import net.coljate.collection.Collection;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableMap;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;
import net.coljate.util.Iterators;

/**
 *
 * @author ollie
 */
public class WrappedMap<K, V>
        extends AbstractMap<K, V>
        implements Map<K, V> {

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
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return Iterators.transform(
                delegate.entrySet().iterator(),
                entry -> new ImmutableEntry<>(entry.getKey(), entry.getValue()));
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
    protected boolean equals(AbstractMap<?, ?> that) {
        return that instanceof WrappedMap
                && Objects.equals(this.delegate, ((WrappedMap) that).delegate);
    }

}
