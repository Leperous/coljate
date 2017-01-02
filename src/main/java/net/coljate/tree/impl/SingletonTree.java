package net.coljate.tree.impl;

import net.coljate.map.ImmutableEntry;
import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableNode;
import net.coljate.tree.ImmutableTree;

/**
 *
 * @author ollie
 */
public class SingletonTree<K, V>
        extends AbstractTree<K, V, ImmutableNode<K, V>>
        implements ImmutableTree<K, V, ImmutableNode<K, V>> {

    private final ImmutableNode<K, V> root;

    public SingletonTree(final K key, final V value) {
        this.root = ImmutableNode.of(key, value);
    }

    @Override
    public ImmutableNode<K, V> root() {
        return root;
    }

    @Override
    public ImmutableTree<K, V, ? extends ImmutableEntry<K, V>> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
