package net.ollie.coljate.map;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.list.ImmutableWrappedArrayList;
import net.ollie.coljate.set.ImmutableSet;
import net.ollie.coljate.set.ImmutableWrappedHashSet;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedHashMap<K, V>
        extends WrappedMap<K, V>
        implements ImmutableMap<K, V>, HashMap<K, V> {

    public static <K, V> ImmutableWrappedHashMap<K, V> copyOf(final Map<K, V> map) {
        return map instanceof ImmutableWrappedHashMap
                ? (ImmutableWrappedHashMap<K, V>) map
                : new ImmutableWrappedHashMap<>(HashMap.copyOf(map));
    }

    public static <K, V> ImmutableWrappedHashMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new ImmutableWrappedHashMap<>(HashMap.copyOf(map));
    }

    private final java.util.HashMap<K, V> delegate;

    ImmutableWrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ImmutableSet<K> keys() {
        return ImmutableWrappedHashSet.copyOf(delegate.keySet());
    }

    @Override
    public ImmutableCollection<V> values() {
        return ImmutableWrappedArrayList.copyOf(delegate.values());
    }

    @Override
    public ImmutableMap<K, V> with(final K key, final V value) {
        if (this.contains(key, value)) {
            return this;
        }
        final java.util.HashMap<K, V> copy = HashMap.copyOf(delegate);
        copy.put(key, value);
        return new ImmutableWrappedHashMap<>(copy);
    }

    @Override
    public ImmutableMap<K, V> without(final Object key) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<KeyValue<K, V>> iterator() {
        return new DelegatedUnmodifiableIterator<>(super.iterator());
    }

    @Override
    public ImmutableMap<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    @Deprecated
    public ImmutableWrappedHashMap<K, V> immutableCopy() {
        return this;
    }

}
