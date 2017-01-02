package net.coljate.tree;

import javax.annotation.Nonnull;

import net.coljate.map.Entry;
import net.coljate.set.Set;
import net.coljate.util.SelfTyped;

/**
 *
 * @author ollie
 */
public interface Node<K, V, N extends Node<K, V, N>> extends Entry<K, V>, SelfTyped<N> {

    @Nonnull
    Set<? extends N> children();

}
