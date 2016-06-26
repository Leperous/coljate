package net.ollie.coljate.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.MutableCollection;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 * @see java.util.Map
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableCollection<MapEntry<K, V>> {

    V put(K key, V value);

    default void putAll(@NonNull final Map<? extends K, ? extends V> map) {
        map.forEach(this::put);
    }

    default V compute(final K key, final BiFunction<? super K, ? super V, ? extends V> compute) {
        final V current = this.get(key);
        final V computed = compute.apply(key, current);
        this.put(key, computed);
        return computed;
    }

    default V computeIfAbsent(final K key, final Function<? super K, ? extends V> compute) {
        final V current = this.get(key);
        if (current == null && !this.containsKey(key)) {
            final V computed = compute.apply(key);
            this.put(key, computed);
            return computed;
        } else {
            return current;
        }
    }

    V deleteKey(Object key);

    @Override
    default boolean add(final MapEntry<K, V> element) {
        this.put(element.key(), element.value());
        return true;
    }

    @Override
    @Deprecated
    default boolean removeOnce(final Object element) {
        return element instanceof MapEntry
                && this.removeEntry((MapEntry) element);
    }

    @Override
    @Deprecated
    default boolean removeAll(final Object element) {
        return this.removeOnce(element);
    }

    default boolean removeEntry(final MapEntry<?, ?> entry) {
        final V current = this.get(entry.key());
        if (Objects.equals(current, entry.value())) {
            this.deleteKey(entry.key());
            return true;
        }
        return false;
    }

    Iterator<? extends MutableMapEntry<K, V>> entries();

    @Override
    default Iterator<MapEntry<K, V>> iterator() {
        return Iterators.transform(this.entries(), Function.identity());
    }

    @Override
    void clear();

}
