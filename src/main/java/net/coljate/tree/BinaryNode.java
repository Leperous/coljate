package net.coljate.tree;

import net.coljate.map.Entry;

/**
 *
 * @author ollie
 */
public interface BinaryNode<K, V> extends Entry<K, V> {

    BinaryNode<K, V> left();

    BinaryNode<K, V> right();

}
