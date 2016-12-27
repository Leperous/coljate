package net.coljate.graph;

import java.util.Objects;

import net.coljate.set.impl.TwoSet;

/**
 * Encapsulates an edge between two vertices.
 *
 * The vertices are reversible if this is a member of an undirected graph.
 *
 * @param <V> vertex type
 * @param <E> edge type
 */
public interface Relationship<V, E> {

    V from();

    V to();

    E edge();

    default TwoSet<V> vertices() {
        return TwoSet.require(this.from(), this.to());
    }

    default boolean containsVertex(final Object vertex) {
        return this.isFrom(vertex) || this.isTo(vertex);
    }

    default boolean isBetween(final Object fromVertex, final Object toVertex) {
        return this.isFrom(fromVertex) && this.isTo(toVertex);
    }

    default boolean isFrom(final Object vertex) {
        return Objects.equals(vertex, this.from());
    }

    default boolean isTo(final Object vertex) {
        return Objects.equals(vertex, this.to());
    }

}
