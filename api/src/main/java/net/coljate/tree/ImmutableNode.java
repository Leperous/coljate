package net.coljate.tree;

import net.coljate.map.ImmutableEntry;
import net.coljate.set.ImmutableSet;
import net.coljate.tree.impl.SimpleImmutableNode;

/**
 *
 * @author ollie
 */
public interface ImmutableNode<K, V, N extends ImmutableNode<K, V, N>>
        extends EntryNode<K, V, N>, ImmutableEntry<K, V> {

    @Override
    ImmutableSet<? extends N> children();

    static <K, V> SimpleImmutableNode<K, V> leaf(final K key, final V value) {
        return SimpleImmutableNode.leaf(key, value);
    }

}
