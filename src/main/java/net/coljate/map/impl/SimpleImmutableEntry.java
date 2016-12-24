package net.coljate.map.impl;

import net.coljate.map.AbstractEntry;
import net.coljate.map.ImmutableEntry;

/**
 *
 * @author ollie
 */
public class SimpleImmutableEntry<K, V> 
        extends AbstractEntry<K, V>
        implements ImmutableEntry<K, V> {

    private final K key;
    private final V value;

    public SimpleImmutableEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K key() {
        return key;
    }

    @Override
    public V value() {
        return value;
    }

}