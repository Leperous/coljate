package net.coljate.tree.search;

import net.coljate.map.Entry;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 */
public interface TreeSearch {

    <K, V, E extends Entry<K, V>> E findEntry(Object key, Tree<K, V, E> tree);

    TreeSearch DEPTH_FIRST_RECURSIVE = new DepthFirstRecursiveTreeSearch();
    TreeSearch DEFAULT = DEPTH_FIRST_RECURSIVE;

}
