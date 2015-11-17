package net.ollie.coljate.maps;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.lists.ImmutableArrayList;
import net.ollie.coljate.sets.ImmutableHashSet;
import net.ollie.coljate.sets.ImmutableSet;

/**
 *
 * @author Ollie
 */
public class ImmutableHashMap<K, V> extends NativeMap<K, V> implements ImmutableMap<K, V> {

    public static <K, V> ImmutableMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new ImmutableHashMap<>(copyIntoHashMap(map));
    }

    private final java.util.HashMap<K, V> delegate;

    ImmutableHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ImmutableSet<K> keys() {
        return ImmutableHashSet.copyOf(delegate.keySet());
    }

    @Override
    public ImmutableCollection<V> values() {
        return ImmutableArrayList.copyOf(delegate.values());
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
