package net.ollie.coljate.map;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.Collection;
import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.set.WrappedSet;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public class WrappedMap<K, V> implements Map<K, V> {

    final java.util.Map<K, V> delegate;

    protected WrappedMap(final java.util.Map<K, V> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public boolean inDomain(final K key) {
        return delegate.containsKey(key);
    }

    @Override
    public int count() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Set<K> keys() {
        return new WrappedSet<>(delegate.keySet());
    }

    @Override
    public Collection<V> values() {
        return new WrappedCollection<>(delegate.values());
    }

    @Override
    public Map<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        return MutableWrappedHashMap.copyOf(delegate);
    }

    @Override
    public ImmutableMap<K, V> immutableCopy() {
        return ImmutableWrappedHashMap.copyOf(delegate);
    }

}
