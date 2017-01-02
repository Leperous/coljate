package net.coljate.tree;

import javax.annotation.CheckForNull;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface BinaryNode<K, V> extends Node<K, V> {

    @CheckForNull
    BinaryNode<K, V> left();

    @CheckForNull
    BinaryNode<K, V> right();

    @Override
    default Set<? extends Node<K, V>> children() {
        return Set.ofNonNull(this.left(), this.right());
    }

    default boolean isLeaf() {
        return this.left() == null
                && this.right() == null;
    }

}
