package net.coljate.tree.search;

import java.util.Objects;

import net.coljate.map.Entry;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 */
public class DepthFirstRecursiveTreeSearch implements TreeSearch {

    protected DepthFirstRecursiveTreeSearch() {
    }

    @Override
    public <K, V> Entry<K, V> get(final Object key, final Tree<K, V> tree) {
        final Entry<K, V> root = tree.root();
        if (root == null || Objects.equals(key, root.key())) {
            return root;
        }
        for (final Tree<K, V> subtree : tree.subtrees(key)) {
            final Entry<K, V> found = this.get(key, subtree);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
