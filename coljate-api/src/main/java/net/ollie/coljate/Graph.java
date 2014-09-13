package net.ollie.coljate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.access.Keyed;
import net.ollie.coljate.imposed.Distinctness.Unique;

import javax.annotation.Nonnull;

/**
 * @param <N> node type
 * @param <V> value type
 * @author Ollie
 */
public interface Graph<N, V>
        extends Keyed.Single<N, V>, Streamable<V> {

    @Nonnull
    Unique<N> neighbours(N node);

    default boolean adjacent(@Nonnull final N node1, @Nonnull final N node2) {
        return this.neighbours(node1).contains(node2);
    }

    @Override
    default boolean isEmpty() {
        return Streamable.super.isEmpty();
    }

    @Override
    Graph.Mutable<N, V> mutableCopy();

    @Override
    Graph.Immutable<N, V> immutableCopy();

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
