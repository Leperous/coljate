package net.ollie.coljate.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

import net.ollie.coljate.MutableCollection;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableCollection<MapEntry<K, V>> {

    V put(K key, V value);

    V delete(Object key);

    @Override
    default boolean add(final MapEntry<K, V> element) {
        this.put(element.key(), element.value());
        return true;
    }

    @Override
    @Deprecated
    default boolean removeOnce(final Object element) {
        return element instanceof MapEntry
                && this.remove((MapEntry) element);
    }

    @Override
    @Deprecated
    default boolean removeAll(final Object element) {
        return this.removeOnce(element);
    }

    default boolean remove(final MapEntry<?, ?> entry) {
        final V current = this.get(entry.key());
        if (Objects.equals(current, entry.value())) {
            this.delete(entry.key());
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
