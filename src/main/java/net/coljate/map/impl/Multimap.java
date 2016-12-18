package net.coljate.map.impl;

import java.util.Iterator;
import java.util.function.Supplier;

import net.coljate.collection.Collection;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.map.LoadingCache;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class Multimap<K, C extends Collection<?>>
        extends AbstractMap<K, C>
        implements LoadingCache<K, C> {

    private final Supplier<? extends C> createCollection;
    final MutableMap<K, C> map;

    protected Multimap(final MutableMap<K, C> map, final Supplier<? extends C> createCollection) {
        this.map = map;
        this.createCollection = createCollection;
    }

    @Override
    public C get(final K key) {
        return map.computeIfAbsent(key, k -> createCollection.get());
    }

    @Override
    public Entry<K, C> entry(final Object key) {
        return map.entry(key);
    }

    @Override
    public Set<? extends K> keys() {
        return map.keys();
    }

    @Override
    public Collection<C> values() {
        return map.values();
    }

    @Override
    public Iterator<Entry<K, C>> iterator() {
        return map.iterator();
    }
    
}
