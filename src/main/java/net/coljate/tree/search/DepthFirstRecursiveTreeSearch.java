package net.coljate.tree.search;

import java.util.Objects;

import net.coljate.map.Entry;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 * @since 1.0
 */
public class DepthFirstRecursiveTreeSearch implements TreeSearch {

    protected DepthFirstRecursiveTreeSearch() {
    }

    @Override
    public <K, V, E extends Entry<K, V>> E findEntry(final Object key, final Tree<K, V, E> tree) {
        final E root = tree.root();
        if (root == null || Objects.equals(key, root.key())) {
            return root;
        }
        for (final Tree<K, V, E> subtree : tree.subtrees(key)) {
            final E found = this.findEntry(key, subtree);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
