package net.coljate.tree.impl;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.tree.ImmutableTree;
import net.coljate.tree.MutableTree;
import net.coljate.tree.Tree;
import net.coljate.tree.impl.LinkedTree.Node;

/**
 *
 * @author ollie
 */
public class LinkedTree<K, V, E extends Node<K, V, E>> implements Tree<K, V, E> {

    private final E root;

    public LinkedTree(final E root) {
        this.root = root;
    }

    @Override
    public E root() {
        return root;
    }

    @Override
    public Collection<? extends LinkedTree<K, V, E>> subtrees(final Object key) {
        final Node<K, V, E> node = this.getEntry(key);
        return node == null
                ? Collection.of()
                : node.subtrees();
    }

    @Override
    public MutableTree<K, V, ?> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableTree<K, V, ?> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public interface Node<K, V, E extends Node<K, V, E>> extends Entry<K, V> {

        Collection<? extends LinkedTree<K, V, E>> subtrees();

    }

}
