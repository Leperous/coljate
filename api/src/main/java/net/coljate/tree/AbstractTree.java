package net.coljate.tree;

import net.coljate.map.AbstractMap;

/**
 *
 * @author ollie
 * @since 1.0
 */
public abstract class AbstractTree<K, V, N extends EntryNode<K, V, N>>
        extends AbstractMap<K, V>
        implements TreeMap<K, V, N> {

}
