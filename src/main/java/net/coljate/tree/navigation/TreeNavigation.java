package net.coljate.tree.navigation;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import net.coljate.list.List;
import net.coljate.list.MutableList;
import net.coljate.tree.Node;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface TreeNavigation {

    default <K, V, N extends Node<K, V>> N findNode(final Tree<K, V, N> tree, final Predicate<? super N> predicate) {
        return this.findNode(tree.root(), predicate);
    }

    <N extends Node<?, ?>> N findNode(N node, Predicate<? super N> predicate);

    default <N extends Node<?, ?>> List<N> collect(final Tree<?, ?, N> tree) {
        final MutableList<N> nodes = MutableList.create(10);
        this.collect(tree.root(), nodes);
        return nodes;
    }

    <N extends Node<?, ?>> void collect(N node, MutableList<N> nodes);

    TreeNavigation DEPTH_FIRST_RECURSIVE = new DepthFirstRecursiveTreeNavigation();
    AtomicReference<TreeNavigation> DEFAULT = new AtomicReference<>(DEPTH_FIRST_RECURSIVE);

    static TreeNavigation getDefault() {
        return DEFAULT.get();
    }

    static void setDefault(final TreeNavigation navigation) {
        Objects.requireNonNull(navigation, "Cannot set null default tree navigation!");
        DEFAULT.set(navigation);
    }

}
