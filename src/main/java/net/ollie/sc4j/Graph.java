package net.ollie.sc4j;

import net.ollie.sc4j.access.Iteratable;
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
        extends Keyed<N, V>, Iteratable<V> {

    boolean adjacent(@Nonnull N node1, @Nonnull N node2);

    @Nonnull
    Unique<N> neighbours(N node);

    @Override
    Graph.Mutable<N, V> mutableCopy();

    @Override
    Graph.Immutable<N, V> immutableCopy();

    interface Mutable<N, V>
            extends Graph<N, V>, Iteratable.Mutable<V> {

        void delete(N node);

    }

    interface Immutable<N, V>
            extends Graph<N, V>, Iteratable.Immutable<V> {

        @Override
        default Graph.Immutable<N, V> immutableCopy() {
            return this;
        }

    }

}
