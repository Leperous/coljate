package net.ollie.coljate.graphs;

import net.ollie.coljate.access.Keyed;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.imposed.Distinctness.Unique;
import net.ollie.coljate.imposed.Mapping.Surjective;

import javax.annotation.Nonnull;

/**
 * @param <K> node type
 * @param <V> value type
 * @author Ollie
 */
public interface Graph<K, V>
        extends Keyed.Single<K, V>, Streamable<V>, Surjective<K, V> {

    @Nonnull
    Unique<K> neighbours(K node);

    default boolean adjacent(@Nonnull final K node1, @Nonnull final K node2) {
        return this.neighbours(node1).contains(node2);
    }

    @Override
    default boolean isEmpty() {
        return Streamable.super.isEmpty();
    }

    @Override
    Graph.Mutable<K, V> mutableCopy();

    @Override
    Graph.Immutable<K, V> immutableCopy();

    interface Node<K, V> {

        K key();

        V value();

    }

    interface Mutable<N, V>
            extends Graph<N, V>, Streamable.Mutable<V> {

        void delete(N node);

    }

    interface Immutable<N, V>
            extends Graph<N, V>, Streamable.Immutable<V> {

        @Override
        Graph.Immutable<N, V> tail();

        @Override
        default Graph.Immutable<N, V> immutableCopy() {
            return this;
        }

    }

}
