package net.coljate.tree;

import net.coljate.map.ImmutableEntry;
import net.coljate.set.ImmutableSet;
import net.coljate.tree.impl.ImmutableLeafNode;

/**
 *
 * @author ollie
 */
public interface ImmutableNode<K, V, N extends ImmutableNode<K, V, N>>
        extends Node<K, V, N>, ImmutableEntry<K, V> {

    @Override
    ImmutableSet<? extends N> children();

    static <K, V> ImmutableLeafNode<K, V> of(final K key, final V value) {
        return ImmutableLeafNode.of(key, value);
    }

}
