package net.coljate.map.impl;

import java.util.function.Function;

import net.coljate.map.Cache;
import net.coljate.map.ConcurrentMap;
import net.coljate.map.MutableMap;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public class ConcurrentCache<K, V> implements Cache<K, V> {

    private final ConcurrentMap<K, Holder<K, V>> cache = ConcurrentMap.createHashMap(10);
    private final Function<? super K, ? extends V> valueFunction;

    public ConcurrentCache(final Function<? super K, ? extends V> valueFunction) {
        this.valueFunction = valueFunction;
    }

    @Override
    public V get(final K key) {
        return cache.computeIfAbsent(key, k -> new Computer()).compute(key);
    }

    @Override
    public V getIfPresent(final Object key) {
        final Holder<K, V> holder = cache.getIfPresent(key);
        return holder == null ? null : holder.current();
    }

    @Override
    public V evict(final Object key) {
        return Functions.ifNonNull(cache.evict(key), Holder::current);
    }

    @Override
    @Deprecated
    public boolean contains(final Object object) {
        return cache.contains(object);
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        throw new UnsupportedOperationException();
    }

    private interface Holder<K, V> {

        V current();

        V compute(K key);

    }

    private final class Computer implements Holder<K, V> {

        final Object lock = new Object();
        int timesAccessed = 0;

        @Override
        public V current() {
            return null;
        }

        @Override
        public V compute(final K key) {
            try {
                synchronized (lock) {
                    lock.wait();
                    if (timesAccessed++ > 1) {
                        return ConcurrentCache.this.get(key);
                    }
                    final V value = valueFunction.apply(key);
                    cache.put(key, new Computed<>(value));
                    lock.notifyAll();
                    return value;
                }
            } catch (final InterruptedException iex) {
                throw new RuntimeException(iex); //TODO 
            }
        }

    }

    private static final class Computed<K, V> implements Holder<K, V> {

        private final V value;

        Computed(final V value) {
            this.value = value;
        }

        @Override
        public V current() {
            return value;
        }

        @Override
        public V compute(final K key) {
            return value;
        }

    }

}
