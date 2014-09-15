package net.ollie.coljate.graphs;

import net.ollie.coljate.sets.Set;
import net.ollie.coljate.imposed.Distinctness.Unique;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @param <N> node type
 * @param <V> value type
 */
public interface Tree<N, V>
        extends Graph<N, V> {

    @CheckForNull
    N root();

    @Override
    default V head() {
        final N root = this.root();
        return root == null ? null : this.get(root);
    }

    @CheckForNull
    N parent(N node);

    @Nonnull
    Unique<N> children(N node);

    @Nonnull
    Unique<? extends Tree<N, V>> subtrees(N node);

    @Override
    Set<N> neighbours(N node);

    @Override
    default boolean isEmpty() {
        return this.root() == null;
    }

    @Override
    Tree.Mutable<N, V> mutableCopy();

    @Override
    Tree.Immutable<N, V> immutableCopy();

    interface Mutable<N, V>
            extends Tree<N, V>, Graph.Mutable<N, V> {

    }

    interface Immutable<N, V>
            extends Tree<N, V>, Graph.Immutable<N, V> {

        @Override
        Set.Immutable<N> children(N node);

        @Override
        default Set.Immutable<N> neighbours(final N node) {
            return this.children(node).andMaybe(this.parent(node));
        }

        @Override
        Unique<? extends Tree.Immutable<N, V>> subtrees(N node);

        @Override
        default Tree.Immutable<N, V> immutableCopy() {
            return this;
        }

    }

}
