package net.coljate.map.impl;

import java.util.Iterator;

import net.coljate.map.Entry;
import net.coljate.map.Map;

/**
 *
 * @author ollie
 */
public class MapIterator<K, V> implements Iterator<Entry<K, V>> {

    private final Map<K, V> map;
    private final Iterator<K> keys;

    public MapIterator(final Map<K, V> map) {
        this.map = map;
        this.keys = map.keys().iterator();
    }

    @Override
    public boolean hasNext() {
        return keys.hasNext();
    }

    @Override
    public Entry<K, V> next() {
        return map.getEntry(keys.next());
    }

}
