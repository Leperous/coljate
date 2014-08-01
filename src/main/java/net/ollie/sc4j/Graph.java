package net.ollie.sc4j;

import net.ollie.sc4j.access.Finite;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.imposed.Unique;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @param <N> node type
 * @param <V> value type
 */
public interface Graph<N, V>
        extends Keyed.Single<N, V>, Finite<V> {

    @Nonnull
    Unique<N> neighbours(N node);

    default boolean adjacent(@Nonnull final N node1, @Nonnull final N node2) {
        return this.neighbours(node1).contains(node2);
    }

    @Override
    default boolean isEmpty() {
        return Finite.super.isEmpty();
    }

    @Override
    Graph.Mutable<N, V> mutableCopy();

    @Override
    Graph.Immutable<N, V> immutableCopy();

    interface Mutable<N, V>
            extends Graph<N, V>, Finite.Mutable<V> {

        void delete(N node);

    }

    interface Immutable<N, V>
            extends Graph<N, V>, Finite.Immutable<V> {

        @Override
        default Graph.Immutable<N, V> immutableCopy() {
            return this;
        }

    }

}
