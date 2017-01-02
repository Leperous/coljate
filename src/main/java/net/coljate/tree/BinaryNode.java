package net.coljate.tree;

import javax.annotation.CheckForNull;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface BinaryNode<K, V, N extends BinaryNode<K, V, N>> extends Node<K, V, N> {

    @CheckForNull
    N left();

    @CheckForNull
    N right();

    @Override
    default Set<? extends N> children() {
        return Set.ofNonNull(this.left(), this.right());
    }

    default boolean isLeaf() {
        return this.left() == null
                && this.right() == null;
    }

}
