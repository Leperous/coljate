package net.coljate.tree.navigation;

import java.util.Objects;

import net.coljate.list.MutableList;
import net.coljate.map.Entry;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 * @since 1.0
 */
public class DepthFirstRecursiveTreeNavigation implements TreeNavigation {

    protected DepthFirstRecursiveTreeNavigation() {
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

    @Override
    public <E extends Entry<?, ?>> MutableList<E> collect(
            final Tree<?, ?, E> tree,
            final MutableList<E> list) {
        final E root = tree.root();
        if (root != null) {
            list.suffix(root);
            for (final Tree<?, ?, E> subtree : tree.subtrees(root.key())) {
                this.collect(subtree, list);
            }
        }
        return list;
    }

}
