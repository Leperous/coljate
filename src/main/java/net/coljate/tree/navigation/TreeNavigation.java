package net.coljate.tree.navigation;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

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

    @CheckForNull
    default <N extends Node<?, ?, N>> N first(final Tree<?, ?, N> tree, final Predicate<? super N> predicate) {
        return this.first(tree.root(), predicate);
    }

    @CheckForNull
    <N extends Node<?, ?, N>> N first(N node, Predicate<? super N> predicate);

    @Nonnull
    default <N extends Node<?, ?, N>> List<N> collect(final Tree<?, ?, N> tree, final Predicate<? super N> predicate) {
        final MutableList<N> nodes = MutableList.create(10);
        this.collect(tree.root(), nodes, predicate);
        return nodes;
    }

    <N extends Node<?, ?, N>> void collect(N node, MutableList<N> nodes, Predicate<? super N> predicate);

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
