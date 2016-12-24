package net.coljate.tree.impl;

import net.coljate.collection.ImmutableCollection;
import net.coljate.map.ImmutableEntry;
import net.coljate.set.ImmutableSet;
import net.coljate.tree.ImmutableTree;

/**
 *
 * @author ollie
 */
public class ImmutableSubtree<K, V>
        extends Subtree<K, V, ImmutableSubtree<K, V>>
        implements ImmutableTree<K, V, ImmutableSubtree<K, V>>, ImmutableEntry<K, V> {

    public static <K, V> ImmutableSubtree<K, V> of(final K key, final V value) {
        return new ImmutableSubtree<>(key, value, ImmutableSet.of());
    }

    @SafeVarargs
    public static <K, V> ImmutableSubtree<K, V> of(final K key, final V value, final ImmutableSubtree<K, V>... children) {
        return new ImmutableSubtree<>(key, value, ImmutableSet.copyOf(children));
    }

    protected ImmutableSubtree(
            final K key,
            final V value,
            final ImmutableSet<? extends ImmutableSubtree<K, V>> children) {
        super(key, () -> value, children);
    }

    @Override
    public ImmutableSubtree<K, V> root() {
        return this;
    }

    @Override
    public ImmutableCollection<? extends ImmutableSubtree<K, V>> subtrees(final Object key) {
        return super.subtrees(key).immutableCopy();
    }

    @Override
    public ImmutableSubtree<K, V> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}