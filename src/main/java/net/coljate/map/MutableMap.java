package net.coljate.map;

import java.util.function.Function;

import net.coljate.MutableCollection;

/**
 *
 * @author ollie
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableCollection<Map.Entry<K, V>> {

    V put(K key, V value);

    V remove(Object key);

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

}
