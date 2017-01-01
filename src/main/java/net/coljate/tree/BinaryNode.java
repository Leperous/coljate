package net.coljate.tree;

import javax.annotation.CheckForNull;

import net.coljate.map.Entry;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface BinaryNode<K, V> extends Entry<K, V> {

    @CheckForNull
    BinaryNode<K, V> left();

    @CheckForNull
    BinaryNode<K, V> right();

}
