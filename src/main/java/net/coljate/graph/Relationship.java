package net.coljate.graph;

import net.coljate.set.impl.TwoSet;

/**
 *
 * @param <V> vertex type
 * @param <E> edge type
 */
public interface Relationship<V, E> {

    TwoSet<V> vertices();

    E edge();

    default boolean containsVertex(final Object vertex) {
        return this.vertices().contains(vertex);
    }

    default boolean containsVertices(final Object vertex1, final Object vertex2) {
        return this.vertices().contains(vertex1, vertex2);
    }

}
