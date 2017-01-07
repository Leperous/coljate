package net.coljate.tree.impl;

import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableNode;
import net.coljate.tree.ImmutableTree;

/**
 *
 * @author ollie
 */
public class EmptyTree<K, V>
        extends AbstractTree<K, V, SimpleImmutableNode<K, V>>
        implements ImmutableTree<K, V, SimpleImmutableNode<K, V>> {

    @Override
    public SimpleImmutableNode<K, V> root() {
        return null;
    }

    @Override
    public SimpleImmutableTree<K, V, SimpleImmutableNode<K, V>> with(final K key, final V value) {
        return new SimpleImmutableTree<>(ImmutableNode.leaf(key, value));
    }

}
