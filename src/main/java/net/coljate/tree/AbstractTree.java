package net.coljate.tree;

import net.coljate.map.AbstractMap;

/**
 *
 * @author ollie
 * @since 1.0
 */
public abstract class AbstractTree<K, V, E extends Node<K, V>>
        extends AbstractMap<K, V>
        implements Tree<K, V, E> {

}
