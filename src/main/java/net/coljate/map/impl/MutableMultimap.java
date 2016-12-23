package net.coljate.map.impl;

import java.util.function.Supplier;

import net.coljate.collection.Collection;
import net.coljate.map.ConcurrentMap;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;
import net.coljate.set.ConcurrentSet;
import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public class MutableMultimap<K, C extends Collection<?>>
        extends Multimap<K, C>
        implements MutableMap<K, C> {

    public static <K, V> MutableMultimap<K, MutableSet<V>> createHashSetMultimap() {
        return new MutableMultimap<>(MutableMap.createHashMap(), MutableSet::createHashSet);
    }

    public static <K, V> MutableMultimap<K, ConcurrentSet<V>> createConcurrentHashSetMultimap() {
        return new MutableMultimap<>(ConcurrentMap.createHashMap(), ConcurrentSet::createHashSet);
    }

    protected MutableMultimap(final MutableMap<K, C> map, final Supplier<? extends C> createCollection) {
        super(map, createCollection);
    }

    @Override
    public MutableEntry<K, C> getEntry(final Object key) {
        return map.getEntry(key);
    }

    @Override
    public C put(final K key, final C value) {
        return map.put(key, value);
    }

    @Override
    public C evict(Object key) {
        return map.evict(key);
    }

    @Override
    public boolean remove(final Object key, final Object value) {
        return map.remove(key, value);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
