package net.ollie.coljate.graph;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public interface BinaryTree<K, V>
        extends Tree<K, V> {

    @Override
    BinaryNode<K, V> root();

    interface BinaryNode<@NonNull K, @Nullable V> extends Node<K, V> {

        BinaryNode<K, V> left();

        BinaryNode<K, V> right();

        @Override
        Set<? extends BinaryNode<K, V>> children();

    }

}
