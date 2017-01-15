package net.coljate.tree.navigation;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.coljate.list.MutableList;
import net.coljate.tree.BinaryNode;
import net.coljate.tree.Node;
import net.coljate.tree.TreeMap;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface TreeNavigation {

    @CheckForNull
    default <N extends Node<?, ?, N>> N first(final TreeMap<?, ?, N> tree, final Predicate<? super N> predicate) {
        return this.first(tree.root(), predicate);
    }

    /**
     *
     * @param <N>
     * @param node
     * @param predicate
     * @return
     */
    @CheckForNull
    <N extends Node<?, ?, N>> N first(N node, Predicate<? super N> predicate);

    /**
     *
     * @param <N>
     * @param tree
     * @param predicate
     * @return
     */
    @Nonnull
    default <N extends Node<?, ?, N>> List<N> collect(final TreeMap<?, ?, N> tree, final Predicate<? super N> predicate) {
        final MutableList<N> nodes = MutableList.create(10);
        this.collect(tree.root(), nodes::suffix, predicate);
        return nodes;
    }

    /**
     *
     * @param <N>
     * @param node
     * @param nodes
     */
    default <N extends Node<?, ?, N>> void collect(final N node, final Consumer<? super N> nodes) {
        this.collect(node, nodes, n -> true);
    }

    /**
     *
     * @param <N>
     * @param node
     * @param nodes
     * @param predicate
     */
    <N extends Node<?, ?, N>> void collect(N node, Consumer<? super N> nodes, Predicate<? super N> predicate);

    /**
     *
     * @param <N>
     * @param node
     * @param nodes
     */
    default <N extends BinaryNode<?, ?, N>> void collectBinaryNodes(
            final N node,
            final Consumer<? super N> nodes) {
        this.collectBinaryNodes(node, nodes, n -> true);
    }

    /**
     *
     * @param <N>
     * @param node
     * @param nodes
     * @param predicate
     */
    default <N extends BinaryNode<?, ?, N>> void collectBinaryNodes(
            final N node,
            final Consumer<? super N> nodes,
            final Predicate<? super N> predicate) {
        this.collect(node, nodes, predicate);
    }

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
