package net.coljate.tree.search;

import net.coljate.map.Entry;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 */
public interface TreeSearch {

    <K, V> Entry<K, V> get(Object key, Tree<K, V> tree);

    TreeSearch DEPTH_FIRST_RECURSIVE = new DepthFirstRecursiveTreeSearch();

}
