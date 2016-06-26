package net.ollie.coljate.map;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.Collection;
import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.map.mixin.CopiedToHashMap;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.set.WrappedSet;
import net.ollie.coljate.utils.Iterators;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public class WrappedMap<K, V>
        extends AbstractMap<K, V>
        implements Map<K, V>, CopiedToHashMap<K, V> {

    private final java.util.Map<K, V> delegate;

    protected WrappedMap(final java.util.Map<K, V> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public V get(final Object key) {
        return delegate.get(key);
    }

    @Override
    public boolean containsKey(final Object key) {
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
    public Iterator<KeyValue<K, V>> iterator() {
        return Iterators.transform(delegate.entrySet().iterator(), WrappedMapEntry::new);
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

}
