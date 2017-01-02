package net.coljate.tree;

import javax.annotation.Nonnull;

import net.coljate.map.Entry;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface Node<K, V> extends Entry<K, V> {

    @Nonnull
    Set<? extends Node<K, V>> children();

}
