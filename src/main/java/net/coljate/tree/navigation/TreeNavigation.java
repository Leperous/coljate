package net.coljate.tree.navigation;

import java.util.concurrent.atomic.AtomicReference;

import net.coljate.list.MutableList;
import net.coljate.map.Entry;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface TreeNavigation {

    <K, V, E extends Entry<K, V>> E findEntry(Object key, Tree<K, V, E> tree);

    <E extends Entry<?, ?>> MutableList<E> collect(Tree<?, ?, E> tree, MutableList<E> list);

    TreeNavigation DEPTH_FIRST_RECURSIVE = new DepthFirstRecursiveTreeNavigation();
    AtomicReference<TreeNavigation> DEFAULT = new AtomicReference<>(DEPTH_FIRST_RECURSIVE);

    static TreeNavigation getDefault() {
        return DEFAULT.get();
    }

    static void setDefault(final TreeNavigation navigation) {
        DEFAULT.set(navigation);
    }

}
