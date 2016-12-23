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
public class LinkedTree<K, V> implements Tree<K, V, Node<K, V>> {

    private final Node<K, V> root;

    public LinkedTree(final Node<K, V> root) {
        this.root = root;
    }

    @Override
    public Node<K, V> root() {
        return root;
    }

    @Override
    public Collection<? extends LinkedTree<K, V>> subtrees(final Object key) {
        final Node<K, V> node = this.entry(key);
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

    public interface Node<K, V> extends Entry<K, V> {

        Collection<? extends LinkedTree<K, V>> subtrees();

    }

}
