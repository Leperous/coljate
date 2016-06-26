package net.ollie.coljate.map;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import net.ollie.coljate.map.mixin.CopiedToHashMap;
import net.ollie.coljate.theory.feature.ThreadSafe;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author ollie
 */
public class MutableConcurrentHashMap<K, V>
        extends WrappedMap<K, V>
        implements MutableMap<K, V>, HashMap<K, V>, CopiedToHashMap<K, V>, ThreadSafe {

    public static <K, V> MutableConcurrentHashMap<K, V> create() {
        return new MutableConcurrentHashMap<>(new ConcurrentHashMap<>());
    }

    public static <K, V> MutableConcurrentHashMap<K, V> copyOf(final java.util.Map<? extends K, ? extends V> map) {
        return new MutableConcurrentHashMap<>(new ConcurrentHashMap<>(map));
    }

    private final java.util.concurrent.ConcurrentHashMap<K, V> delegate;

    MutableConcurrentHashMap(final java.util.concurrent.ConcurrentHashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public java.util.concurrent.ConcurrentHashMap<K, V> delegate() {
        return delegate;
    }

    @Override
    public java.util.concurrent.ConcurrentHashMap<K, V> copyDelegate() {
        return new java.util.concurrent.ConcurrentHashMap<>(delegate);
    }

    @Override
    public V put(final K key, final V value) {
        return delegate.put(key, value);
    }

    @Override
    public V putIfAbsent(final K key, final V value) {
        return delegate.putIfAbsent(key, value);
    }

    @Override
    public V computeIfAbsent(final K key, final Function<? super K, ? extends V> compute) {
        return delegate.computeIfAbsent(key, compute);
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
    public Iterator<? extends MutableKeyValue<K, V>> entries() {
        return Iterators.transform(delegate.entrySet().iterator(), WrappedMapEntry::new);
    }

}
