package net.coljate.tree.impl;

import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableNode;
import net.coljate.tree.ImmutableTreeMap;

/**
 *
 * @author ollie
 */
public class EmptyTreeMap<K, V>
        extends AbstractTree<K, V, SimpleImmutableNode<K, V>>
        implements ImmutableTreeMap<K, V, SimpleImmutableNode<K, V>> {

    @Override
    public SimpleImmutableNode<K, V> root() {
        return null;
    }

    @Override
    public SimpleImmutableTreeMap<K, V, SimpleImmutableNode<K, V>> with(final K key, final V value) {
        return new SimpleImmutableTreeMap<>(ImmutableNode.leaf(key, value));
    }

}
