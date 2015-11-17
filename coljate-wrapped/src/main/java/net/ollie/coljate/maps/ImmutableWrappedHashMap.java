package net.ollie.coljate.maps;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.lists.ImmutableWrappedArrayList;
import net.ollie.coljate.sets.ImmutableWrappedHashSet;
import net.ollie.coljate.sets.ImmutableSet;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedHashMap<K, V> extends WrappedHashMap<K, V> implements ImmutableMap<K, V> {

    public static <K, V> ImmutableMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new ImmutableWrappedHashMap<>(copyIntoHashMap(map));
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
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<MapEntry<K, V>> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableMap<K, V> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
