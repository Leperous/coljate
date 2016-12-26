package net.coljate.graph;

import net.coljate.graph.Graph.Relationship;
import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public interface MutableGraph<X, V, E>
        extends Graph<X, V, E>, MutableSet<Relationship<X, E>> {

    default boolean addVertex(final X vertex) {
        return this.addVertex(vertex, null);
    }

    boolean addVertex(X vertex, V value);

    boolean removeVertex(Object vertex);

    default V putVertex(final X vertex, final V value) {
        final V oldValue = this.getVertexValue(vertex);
        this.addVertex(vertex, value);
        return oldValue;
    }

    boolean addEdge(final X fromVertex, final X toVertex, final E edge);

    @Override
    default boolean add(final Relationship<X, E> relationship) {
        return this.addEdge(relationship.fromVertex(), relationship.toVertex(), relationship.edge());
    }

    default E putEdge(final X fromVertex, final X toVertex, final E edge) {
        final E oldEdge = this.getEdge(fromVertex, toVertex);
        this.addEdge(fromVertex, toVertex, edge);
        return oldEdge;
    }

    E removeEdge(Object fromVertex, Object toVertex);

}
