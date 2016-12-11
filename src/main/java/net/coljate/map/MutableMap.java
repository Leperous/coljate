package net.coljate.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;

import net.coljate.collection.MutableCollection;
import net.coljate.map.impl.MutableWrappedHashMap;
import net.coljate.map.impl.MutableWrappedMap;
import net.coljate.set.MutableSet;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableSet<Entry<K, V>> {

    V put(K key, V value);

    boolean remove(Object key, Object value);

    @Override
    MutableCollection<V> values();

    @Override
    MutableEntry<K, V> entry(Object key);

    default boolean add(final K key, final V value) {
        final V current = Functions.ifNonNull(this.entry(key), Entry::value);
        if (!Objects.equals(current, value)) {
            this.put(key, value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    default boolean add(final Entry<K, V> entry) {
        return this.add(entry.key(), entry.value());
    }

    default V putIfAbsent(final K key, final V value) {
        final V current = this.get(key);
        return current == null
                ? this.put(key, value)
                : current;
    }

    default V computeIfAbsent(final K key, final Function<K, V> supplier) {
        final V current = this.get(key);
        if (current == null) {
            final V newValue = supplier.apply(key);
            if (newValue != null) {
                this.put(key, newValue);
                return newValue;
            }
        }
        return current;
    }

    default boolean replace(final K key, final V expectedValue, final V replacementValue) {
        final Entry<K, V> current = this.entry(key);
        if (current == null || !Objects.equals(current.value(), expectedValue)) {
            return false;
        }
        this.put(key, replacementValue);
        return true;
    }

    @Deprecated
    @Override
    default boolean removeAll(final Object object) {
        return this.removeFirst(object);
    }

    @Deprecated
    @Override
    default boolean removeFirst(final Object object) {
        return object instanceof Entry
                && this.remove((Entry) object);
    }

    @Deprecated
    default boolean remove(final Object entry) {
        return entry instanceof Entry
                && this.remove((Entry) entry);
    }

    default boolean remove(final Entry<?, ?> entry) {
        return this.remove(entry.key(), entry.value());
    }

    default V removeValue(final Object key) {
        final Entry<K, V> entry = this.entry(key);
        return entry != null && this.remove(entry)
                ? entry.value()
                : null;
    }

    default boolean removeAllMatchingEntries(final BiPredicate<? super K, ? super V> predicate) {
        boolean removed = false;
        for (final Iterator<Entry<K, V>> iterator = this.iterator(); iterator.hasNext();) {
            final Entry<K, V> entry = iterator.next();
            if (predicate.test(entry.key(), entry.value())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    default boolean removeFirstValue(final Object value) {
        return this.values().removeFirst(value);
    }

    default boolean removeAllValues(final Object value) {
        return this.values().removeAll(value);
    }

    static <K, V> MutableMap<K, V> viewOf(final java.util.Map<K, V> map) {
        return MutableWrappedMap.viewOf(map);
    }

    static <K, V> MutableMap<K, V> createHashMap(final int initialCapacity) {
        return MutableWrappedHashMap.createHashMap(initialCapacity);
    }

    static <K, V> MutableMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return MutableWrappedHashMap.copyOf(map);
    }

    public interface MutableEntry<K, V> extends Entry<K, V> {

        void setValue(V value);

    }

}
