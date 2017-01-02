package net.coljate.tree.impl;

import net.coljate.map.impl.SimpleImmutableEntry;
import net.coljate.set.ImmutableSet;
import net.coljate.tree.ImmutableNode;

/**
 *
 * @author ollie
 */
public class SimpleImmutableNode<K, V>
        extends SimpleImmutableEntry<K, V>
        implements ImmutableNode<K, V> {

    public static <K, V> ImmutableNode<K, V> of(final K key, final V value) {
        return new SimpleImmutableNode<>(key, value, ImmutableSet.of());
    }

    private final ImmutableSet<? extends ImmutableNode<K, V>> children;

    protected SimpleImmutableNode(final K key, final V value, final ImmutableSet<? extends ImmutableNode<K, V>> children) {
        super(key, value);
        this.children = children;
    }

    @Override
    public ImmutableSet<? extends ImmutableNode<K, V>> children() {
        return children;
    }

}
