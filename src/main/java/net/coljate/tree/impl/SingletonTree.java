package net.coljate.tree.impl;

import net.coljate.collection.Collection;
import net.coljate.map.ImmutableEntry;
import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableTree;

/**
 *
 * @author ollie
 */
public class SingletonTree<K, V>
        extends AbstractTree<K, V, ImmutableEntry<K, V>>
        implements ImmutableTree<K, V, ImmutableEntry<K, V>> {

    private final K key;
    private final V value;

    public SingletonTree(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public ImmutableEntry<K, V> root() {
        return new ImmutableEntry<>(key, value);
    }

    @Override
    public Collection<? extends ImmutableTree<K, V, ImmutableEntry<K, V>>> subtrees(Object key) {
        return Collection.of();
    }

    @Override
    public ImmutableTree<K, V, ? extends ImmutableEntry<K, V>> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
