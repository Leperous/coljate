package net.coljate.tree;

import net.coljate.map.ImmutableEntry;
import net.coljate.set.ImmutableSet;
import net.coljate.tree.impl.SimpleImmutableNode;

/**
 *
 * @author ollie
 */
public interface ImmutableNode<K, V>
        extends Node<K, V>, ImmutableEntry<K, V> {

    @Override
    ImmutableSet<? extends ImmutableNode<K, V>> children();

    static <K, V> ImmutableNode<K, V> of(final K key, final V value) {
        return SimpleImmutableNode.of(key, value);
    }

}
