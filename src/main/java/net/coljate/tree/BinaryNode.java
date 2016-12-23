package net.coljate.tree;

import net.coljate.map.Entry;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface BinaryNode<K, V> extends Entry<K, V> {

    BinaryNode<K, V> left();

    BinaryNode<K, V> right();

}
