package net.ollie.coljate.maps;

import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.lists.ImmutableArrayList;
import net.ollie.coljate.sets.ImmutableHashSet;
import net.ollie.coljate.sets.ImmutableSet;

/**
 *
 * @author Ollie
 */
public class NativeImmutableHashMap<K, V> extends NativeMap<K, V> implements ImmutableMap<K, V> {

    public static <K, V> ImmutableMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new NativeImmutableHashMap<>(copyIntoHashMap(map));
    }

    private final java.util.HashMap<K, V> delegate;

    NativeImmutableHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ImmutableSet<K> keys() {
        return ImmutableHashSet.copyOf(delegate.keySet());
    }

    @Override
    public ImmutableSet<? extends ImmutableMapEntry<K, V>> entries() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableCollection<V> values() {
        return ImmutableArrayList.copyOf(delegate.values());
    }

}
