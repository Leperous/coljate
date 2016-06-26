package net.ollie.coljate.map;

import java.util.Iterator;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class MutableWrappedMap<@NonNull K, @Nullable V>
        extends WrappedMap<K, V>
        implements MutableMap<K, V> {

    private final java.util.Map<K, V> delegate;

    protected MutableWrappedMap(final java.util.Map<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public V put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    public V deleteKey(final Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Iterator<? extends MutableMapEntry<K, V>> entries() {
        return Iterators.transform(delegate.entrySet().iterator(), WrappedMapEntry::new);
    }

}
