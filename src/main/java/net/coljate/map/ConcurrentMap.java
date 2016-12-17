package net.coljate.map;

import java.util.function.Function;

import net.coljate.map.impl.ConcurrentWrappedHashMap;
import net.coljate.set.ConcurrentSet;

/**
 *
 * @author ollie
 * @see java.util.concurrent.ConcurrentMap
 */
public interface ConcurrentMap<K, V>
        extends MutableMap<K, V>, ConcurrentSet<Entry<K, V>> {
    
    @Override
    ConcurrentMap<K, V> mutableCopy();
    
    @Override
    V putIfAbsent(K key, V value);
    
    @Override
    V computeIfAbsent(K key, Function<K, V> supplier);
    
    @Override
    boolean replace(K key, V expectedValue, V replacementValue);
    
    static <K, V> ConcurrentMap<K, V> createHashMap() {
        return ConcurrentWrappedHashMap.create();
    }
    
    static <K, V> ConcurrentMap<K, V> createHashMap(final int initialCapacity) {
        return ConcurrentWrappedHashMap.create(initialCapacity);
    }
    
}
