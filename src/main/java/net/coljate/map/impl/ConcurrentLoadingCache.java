package net.coljate.map.impl;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.map.ConcurrentMap;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.map.LoadingCache;
import net.coljate.set.Set;
import net.coljate.util.Iterators;

/**
 *
 * @author ollie
 */
public class ConcurrentLoadingCache<K, V> implements LoadingCache<K, V> {

    private final ConcurrentMap<K, Computer<K, V>> map = ConcurrentMap.createHashMap(10);
    private final Function<? super K, ? extends V> valueFunction;

    public ConcurrentLoadingCache(final Function<? super K, ? extends V> valueFunction) {
        this.valueFunction = valueFunction;
    }

    @Override
    public V get(final K key) {
        return map.computeIfAbsent(key, k -> new Computing()).compute(key);
    }

    @Override
    public V getIfPresent(final Object key) {
        final Computer<K, V> holder = map.getIfPresent(key);
        return holder == null ? null : holder.current();
    }

    @Override
    public boolean containsEntry(final Entry<?, ?> entry) {
        final Entry<K, Computer<K, V>> current = map.getEntry(entry.key());
        return current != null
                && !current.value().isComputing()
                && Objects.equals(current.key(), entry.key())
                && Objects.equals(current.value().current(), entry.value());
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set<K> keys() {
        return map.keys();
    }

    @Override
    public Collection<V> values() {
        return map.values().transform(Computer::current);
    }

    @Override
    public Entry<K, V> getEntry(final Object key) {
        return this.translate(map.getEntry(key));
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return Iterators.filter(Iterators.transform(map.iterator(), this::translate), Objects::nonNull);
    }

    private ImmutableEntry<K, V> translate(final Entry<K, Computer<K, V>> entry) {
        return entry == null || entry.value().isComputing()
                ? null
                : ImmutableEntry.of(entry.key(), entry.value().current());
    }

    private interface Computer<K, V> {

        V current();

        V compute(K key);

        boolean isComputing();

    }

    private final class Computing implements Computer<K, V> {

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
                        return ConcurrentLoadingCache.this.get(key);
                    }
                    final V value = valueFunction.apply(key);
                    map.put(key, new Computed<>(value));
                    lock.notifyAll();
                    return value;
                }
            } catch (final InterruptedException iex) {
                throw new RuntimeException(iex); //TODO 
            }
        }

        @Override
        public boolean isComputing() {
            return true;
        }

    }

    private static final class Computed<K, V> implements Computer<K, V> {

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

        @Override
        public boolean isComputing() {
            return false;
        }

    }

}
