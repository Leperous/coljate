package net.coljate.map.impl;

import javax.annotation.CheckForNull;

import net.coljate.map.AbstractEntry;
import net.coljate.map.Entry;
import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
public class ViewEntry<K, V> extends AbstractEntry<K, V> {

    @CheckForNull
    public static <K, V> Entry<K, V> viewOf(final Object key, final Map<K, ? extends V> map) {
        return map.containsKey(key)
                ? new ViewEntry<>((K) key, map)
                : null;
    }

    private final K key;
    private final Map<K, ? extends V> map;

    public ViewEntry(final K key, final Map<K, ? extends V> map) {
        this.key = key;
        this.map = map;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return map.get(key);
    }

}
