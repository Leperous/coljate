package net.ollie.coljate.graphs;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface BinaryTree<K, V>
        extends Tree<K, V> {

    @Nonnull
    BinaryTree<K, V> left(K node);

    @Nonnull
    BinaryTree<K, V> right(K node);

}
