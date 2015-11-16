package net.ollie.coljate.graphs;

import net.ollie.coljate.sets.Set;
import net.ollie.coljate.imposed.Distinctness.Unique;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * A rooted graph in which any two vertices are connected by exactly one path.
 *
 * @author Ollie
 * @param <K> node type
 * @param <V> value type
 */
public interface Tree<K, V>
        extends Graph<K, V> {

    @CheckForNull
    K root();

    @Override
    default V head() {
        final K root = this.root();
        return root == null ? null : this.get(root);
    }

    @CheckForNull
    K parent(K node);

    @Nonnull
    Unique<K> children(K node);

    @Nonnull
    Unique<? extends Tree<K, V>> subtrees(K node);

    @Override
    Set<K> neighbours(K node);

    @Override
    default boolean isEmpty() {
        return this.root() == null;
    }

    @Override
    Tree.Mutable<K, V> mutableCopy();

    @Override
    Tree.Immutable<K, V> immutableCopy();

    interface Mutable<N, V>
            extends Tree<N, V>, Graph.Mutable<N, V> {

    }

    interface Immutable<K, V>
            extends Tree<K, V>, Graph.Immutable<K, V> {

        @Override
        Set.Immutable<K> children(K node);

        @Override
        default Set.Immutable<K> neighbours(final K node) {
            return this.children(node).andMaybe(this.parent(node));
        }

        @Override
        Unique<? extends Tree.Immutable<K, V>> subtrees(K node);

        @Override
        default Tree.Immutable<K, V> immutableCopy() {
            return this;
        }

    }

}
