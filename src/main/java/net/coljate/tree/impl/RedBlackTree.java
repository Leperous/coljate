package net.coljate.tree.impl;

import java.util.Comparator;
import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.coljate.map.AbstractEntry;
import net.coljate.map.Entry;
import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableBinaryTree;
import net.coljate.tree.MutableBinaryTree;
import net.coljate.tree.MutableBinaryTree.MutableBinaryNode;
import net.coljate.tree.MutableSortedTree;
import net.coljate.tree.impl.RedBlackTree.RedBlackNode;

/**
 *
 * @author ollie
 * @see java.util.TreeMap
 * @see <a href="https://www.cs.auckland.ac.nz/software/AlgAnim/red_black.html">Red-Black Trees</a>
 */
public class RedBlackTree<K, V>
        extends AbstractTree<K, V, RedBlackNode<K, V>>
        implements MutableBinaryTree<K, V, RedBlackNode<K, V>>, MutableSortedTree<K, V, RedBlackNode<K, V>> {

    public static <K extends Comparable<? super K>, V> RedBlackTree<K, V> keyComparing() {
        return keyComparing(Comparator.naturalOrder());
    }

    public static <K, V> RedBlackTree<K, V> keyComparing(final Comparator<? super K> comparator) {
        return new RedBlackTree<>((e1, e2) -> comparator.compare(e1.key(), e2.key()));
    }

    private static final boolean BLACK = true, RED = !BLACK;
    private final Comparator<? super Entry<K, V>> comparator;
    private RedBlackNode<K, V> root;
    private int count;

    protected RedBlackTree(final Comparator<? super Entry<K, V>> comparator) {
        this(null, comparator);
    }

    protected RedBlackTree(final RedBlackNode<K, V> root, final Comparator<? super Entry<K, V>> comparator) {
        this.root = root;
        this.count = root == null ? 0 : root.count();
        this.comparator = Objects.requireNonNull(comparator);
    }

    @Override
    public RedBlackNode<K, V> root() {
        return root;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public V put(final K key, final V value) {
        final RedBlackNode<K, V> node = new RedBlackNode<>(key, value, BLACK);
        //Set root if there isn't one
        if (root == null) {
            root = node;
            return null;
        }
        //Find the appropriate parent for this node
        RedBlackNode<K, V> parent, current = root;
        int comparison;
        do {
            parent = current;
            comparison = comparator.compare(node, current);
            if (comparison < 0) {
                current = current.left;
            } else if (comparison > 0) {
                current = current.right;
            } else {
                return current.getAndSetValue(value);
            }
        } while (current != null);
        //Attach to the correct side
        if (comparison < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        this.rebalance(node);
        return null;
    }

    private void rebalance(@Nonnull RedBlackNode<K, V> node) {
        node.colour = RED;
        while (node != null && node != root && node.parent.colour != RED) {
            if(node.parent == left(parent(node.parent))) {
                
            }
        }
        throw new UnsupportedOperationException();
    }
    
    @CheckForNull
    private static <K,V> RedBlackNode<K,V> parent(final RedBlackNode<K,V> node) {
        return node == null ? null : node.parent;
    }
    @CheckForNull
    private static <K,V> RedBlackNode<K,V> left(final RedBlackNode<K,V> node) {
        return node == null ? null : node.left;
    }

    private void rotateLeft(@Nonnull final RedBlackNode<K, V> node) {
        final RedBlackNode<K, V> previous = node.left;
        node.right = previous.left;
        if (previous.left != null) {
            previous.left.parent = node;
        }
        previous.parent = node.parent;
        if (node.parent == null) {
            this.root = previous;
        } else if (node == node.parent.left) {
            node.parent.left = previous;
        } else {
            node.parent.right = previous;
        }
        previous.left = node;
        node.parent = previous;
    }

    @Override
    public boolean remove(final RedBlackNode<K, V> node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public RedBlackTree<K, V> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableBinaryTree<K, V, ?> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Comparator<? super Entry<K, V>> comparator() {
        return comparator;
    }

    @Override
    public RedBlackNode<K, V> greatest() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RedBlackNode<K, V> least() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static final class RedBlackNode<K, V>
            extends AbstractEntry<K, V>
            implements MutableBinaryNode<K, V, RedBlackNode<K, V>> {

        private final K key;
        @CheckForNull
        private V value;
        @CheckForNull
        private RedBlackNode<K, V> parent, left, right;
        private boolean colour = BLACK;

        RedBlackNode(final K key, final V value, final boolean black) {
            this.key = key;
            this.value = value;
            this.colour = black;
        }

        void setLeft(final RedBlackNode<K, V> left) {
            this.left = left;
            left.parent = this;
        }

        void setRight(final RedBlackNode<K, V> right) {
            this.right = right;
            right.parent = this;
        }

        public int count() {
            return 1 + (left == null ? 0 : left.count()) + (right == null ? 0 : right.count());
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
