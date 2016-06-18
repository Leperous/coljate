package net.ollie.coljate.map;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.Collection;
import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.set.WrappedSet;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class WrappedMap<K, V>
        extends AbstractMap<K, V>
        implements Map<K, V> {

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
        return Iterators.transform(delegate.entrySet().iterator(), EntryWrapper::new);
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

    private static final class EntryWrapper<K, V> implements MapEntry<K, V> {

        private final java.util.Map.Entry<K, V> delegate;

        EntryWrapper(java.util.Map.Entry<K, V> delegate) {
            this.delegate = delegate;
        }

        @Override
        public K key() {
            return delegate.getKey();
        }

        @Override
        public V value() {
            return delegate.getValue();
        }

    }

}
