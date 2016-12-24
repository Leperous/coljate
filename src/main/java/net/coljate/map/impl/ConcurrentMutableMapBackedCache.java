package net.coljate.map.impl;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

import net.coljate.collection.Collection;
import net.coljate.map.ConcurrentCache;
import net.coljate.map.ConcurrentMap;
import net.coljate.map.Entry;
import net.coljate.map.MutableEntry;
import net.coljate.set.Set;
import net.coljate.util.Functions;
import net.coljate.util.Iterators;

/**
 *
 * @author ollie
 */
public class ConcurrentMutableMapBackedCache<K, V> implements ConcurrentCache<K, V> {

    private final ConcurrentMap<K, Computer<K, V>> map = ConcurrentMap.createHashMap(10);
    private final Function<? super K, ? extends V> valueFunction;

    public ConcurrentMutableMapBackedCache(final Function<? super K, ? extends V> valueFunction) {
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
    public V put(final K key, final V value) {
        return Functions.ifNonNull(map.put(key, new Computed<>(value)), Computer::current);
    }

    @Override
    public boolean remove(final Object key, final Object value) {
        return map.remove(key, new Computed<>(value));
    }

    @Override
    public void clear() {
        map.clear();
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
    public V putIfAbsent(final K key, final V value) {
        return Functions.ifNonNull(map.putIfAbsent(key, new Computed<>(value)), Computer::current);
    }

    @Override
    public V computeIfAbsent(K key, Function<K, V> supplier) {
        return Functions.ifNonNull(map.computeIfAbsent(key, k -> new Computed<>(supplier.apply(k))), Computer::current);
    }

    @Override
    public boolean replace(final K key, final V expectedValue, final V replacementValue) {
        return map.replace(key, new Computed<>(expectedValue), new Computed<>(replacementValue));
    }

    @Override
    public MutableEntry<K, V> getEntry(final Object key) {
        return this.translate(map.getEntry(key));
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return Iterators.filter(Iterators.transform(map.iterator(), this::translate), Objects::nonNull);
    }

    private MutableEntry<K, V> translate(final Entry<K, Computer<K, V>> entry) {
        return entry == null
                ? null
                : new EntryWriter(entry.key());
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
            synchronized (lock) {
                if (timesAccessed++ > 1) {
                    return ConcurrentMutableMapBackedCache.this.get(key);
                }
                final V value = valueFunction.apply(key);
                map.put(key, new Computed<>(value));
                return value;
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

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 89 * hash + Objects.hashCode(this.value);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Computed
                    && Objects.equals(value, ((Computed) obj).value);
        }

    }

    private final class EntryWriter implements MutableEntry<K, V> {

        private final K key;

        EntryWriter(final K key) {
            this.key = key;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return get(key);
        }

        @Override
        public void setValue(final V value) {
            put(key, value);
        }

    }

}
