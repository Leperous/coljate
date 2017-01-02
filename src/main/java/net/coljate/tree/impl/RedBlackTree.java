package net.coljate.tree.impl;

import javax.annotation.CheckForNull;

import net.coljate.map.AbstractEntry;
import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableBinaryTree;
import net.coljate.tree.MutableBinaryTree;
import net.coljate.tree.MutableBinaryTree.MutableBinaryNode;
import net.coljate.tree.impl.RedBlackTree.RedBlackNode;

/**
 *
 * @author ollie
 * @see java.util.TreeMap
 */
public class RedBlackTree<K, V>
        extends AbstractTree<K, V, RedBlackNode<K, V>>
        implements MutableBinaryTree<K, V, RedBlackNode<K, V>> {

    private static final boolean BLACK = true, RED = false;
    private RedBlackNode<K, V> root;

    protected RedBlackTree(final RedBlackNode<K, V> root) {
        this.root = root;
    }

    @Override
    public RedBlackNode<K, V> root() {
        return root;
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RedBlackTree<K, V> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableBinaryTree<K, V, ?> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static class RedBlackNode<K, V>
            extends AbstractEntry<K, V>
            implements MutableBinaryNode<K, V, RedBlackNode<K, V>> {

        private final K key;
        @CheckForNull
        private V value;
        @CheckForNull
        private RedBlackNode<K, V> left, right;
        private boolean black = BLACK;

        RedBlackNode(final K key, final V value, final boolean black) {
            this.key = key;
            this.value = value;
            this.black = black;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public void setValue(final V value) {
            this.value = value;
        }

        @Override
        public RedBlackNode<K, V> left() {
            return left;
        }

        @Override
        public RedBlackNode<K, V> right() {
            return right;
        }

        @Override
        public RedBlackNode<K, V> self() {
            return this;
        }

    }

}
