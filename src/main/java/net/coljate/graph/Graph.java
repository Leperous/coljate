package net.coljate.graph;

import java.util.Objects;

import net.coljate.graph.Graph.Relationship;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.Set;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 * @param <X> type of vertex.
 * @param <V> type of value associated with vertices.
 * @param <E> type of edge.
 */
public interface Graph<X, V, E> extends Set<Relationship<X, E>> {

    Map<X, V> vertices();

    Set<? extends Entry<X, V>> getNeighbours(Object vertex);

    default boolean containsVertex(final Object vertex) {
        return this.vertices().containsKey(vertex);
    }

    default Entry<X, V> getVertex(final Object vertex) {
        return this.vertices().getEntry(vertex);
    }

    default boolean hasVertex(final Object vertex) {
        return this.getVertex(vertex) != null;
    }

    default V getVertexValue(final X vertex) {
        return this.vertices().get(vertex);
    }

    default Set<? extends Relationship<X, E>> getRelationships(final Object vertex) {
        return this.filter(relationship -> relationship.containsVertex(vertex));
    }

    default Set<? extends Relationship<X, E>> getRelationshipsFrom(final Object vertex) {
        return this.filter(relationship -> Objects.equals(vertex, relationship.fromVertex()));
    }

    default Set<? extends Relationship<X, E>> getRelationshipsTo(final Object vertex) {
        return this.filter(relationship -> Objects.equals(vertex, relationship.toVertex()));
    }

    default Relationship<X, E> getRelationship(final Object fromVertex, final Object toVertex) {
        return this.first(relationship -> relationship.isVertices(fromVertex, toVertex));
    }

    default boolean hasEdge(final Object vertex1, final Object vertex2) {
        return this.getRelationship(vertex1, vertex2) != null;
    }

    default E getEdge(final X fromVertex, final X toVertex) {
        return Functions.ifNonNull(this.getRelationship(fromVertex, toVertex), Relationship::edge);
    }

    default Set<E> edges() {
        return this.transform(Relationship::edge).distinct();
    }

    default boolean containsEdge(final Object edge) {
        return this.edges().contains(edge);
    }

    @Override
    MutableGraph<X, V, E> mutableCopy();

    @Override
    ImmutableGraph<X, V, E> immutableCopy();

    /**
     * Two vertices and the edge between them.
     *
     * @param <X>
     * @param <E>
     */
    interface Relationship<X, E> {

        X fromVertex();

        X toVertex();

        E edge();

        default boolean containsVertex(final Object vertex) {
            return Objects.equals(vertex, this.fromVertex())
                    || Objects.equals(vertex, this.toVertex());
        }

        default boolean isVertices(final Object fromVertex, final Object toVertex) {
            return Objects.equals(fromVertex, this.fromVertex())
                    && Objects.equals(toVertex, this.toVertex());
        }

    }

}
