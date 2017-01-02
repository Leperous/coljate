package net.coljate.tree.impl;

import net.coljate.map.impl.SimpleImmutableEntry;
import net.coljate.set.ImmutableSet;
import net.coljate.tree.ImmutableNode;

/**
 *
 * @author ollie
 */
public class ImmutableLeafNode<K, V>
        extends SimpleImmutableEntry<K, V>
        implements ImmutableNode<K, V, ImmutableLeafNode<K, V>> {

    public static <K, V> ImmutableLeafNode<K, V> of(final K key, final V value) {
        return new ImmutableLeafNode<>(key, value);
    }

    protected ImmutableLeafNode(final K key, final V value) {
        super(key, value);
    }

    @Override
    public ImmutableSet<? extends ImmutableLeafNode<K, V>> children() {
        return ImmutableSet.of();
    }

}
