package net.coljate.map;

import java.util.Objects;
import java.util.function.Function;

import net.coljate.collection.MutableCollection;
import net.coljate.map.impl.MutableWrappedHashMap;
import net.coljate.map.impl.MutableWrappedMap;

/**
 *
 * @author ollie
 */
public interface MutableMap<K, V> extends Map<K, V>, MutableCollection<Entry<K, V>> {
    
    V put(K key, V value);
    
    V remove(Object key);
    
    @Override
    MutableEntry<K, V> entry(Object key);
    
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
    
    default boolean remove(final Entry<?, ?> entry) {
        final Entry<K, V> current = this.entry(entry.key());
        if (Objects.equals(current, entry)) {
            this.remove(entry.key());
            return true;
        } else {
            return false;
        }
    }
    
    static <K, V> MutableMap<K, V> viewOf(final java.util.Map<K, V> map) {
        return MutableWrappedMap.viewOf(map);
    }    
    
    static <K, V> MutableMap<K, V> createHashMap(final int initialCapacity) {
        return MutableWrappedHashMap.create(initialCapacity);
    }
    
    static <K, V> MutableMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return MutableWrappedHashMap.copyOf(map);
    }
    
    public interface MutableEntry<K, V> extends Entry<K, V> {
        
        void setValue(V value);
        
    }
    
}
