package net.coljate.tree.impl;

import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableNode;
import net.coljate.tree.ImmutableTreeMap;

/**
 *
 * @author ollie
 */
public class SimpleImmutableTreeMap<K, V, N extends ImmutableNode<K, V, N>>
        extends AbstractTree<K, V, N>
        implements ImmutableTreeMap<K, V, N> {

    private final N root;

    public SimpleImmutableTreeMap(final N root) {
        this.root = root;
    }

    @Override
    public N root() {
        return root;
    }

    @Override
    public SimpleImmutableTreeMap<K, V, N> immutableCopy() {
        return this;
    }

    @Override
    public ImmutableTreeMap<K, V, ?> with(final K key, final V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
