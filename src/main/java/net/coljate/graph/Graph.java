package net.coljate.graph;

import java.util.Objects;

import net.coljate.collection.Collection;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.coljate.util.Functions;

/**
 * A data structure where pairs of objects are "related" somehow.
 *
 * @author ollie
 * @param <V> vertex type
 * @param <E> edge type
 */
public interface Graph<V, E> extends Set<Relationship<V, E>> {

    default Set<V> vertices() {
        final MutableSet<V> vertices = MutableSet.createHashSet();
        this.forEach(relationship -> vertices.addAll(relationship.vertices()));
        return vertices;
    }

    /**
     * @return the number of vertices.
     */
    default int order() {
        return this.vertices().count();
    }

    default Collection<E> edges() {
        return this.transform(Relationship::edge);
    }

    /**
     * @return the number of edges.
     */
    default int size() {
        return this.edges().count();
    }

    default Relationship<V, E> relationshipBetween(final Object fromVertex, final Object toVertex) {
        return this.first(relationship -> relationship.isBetween(fromVertex, toVertex));
    }

    default Map<V, E> neighbours(final V vertex) {
        final MutableMap<V, E> neighbours = MutableMap.createHashMap();
        final Set<Relationship<V, E>> relevant = this.filter(relationship -> relationship.containsVertex(vertex));
        relevant.forEach(relationship -> neighbours.put(relationship.otherVertex(vertex).get(), relationship.edge()));
        return neighbours;
    }

    default E edgeBetween(final Object fromVertex, final Object toVertex) {
        return Functions.ifNonNull(this.relationshipBetween(fromVertex, toVertex), Relationship::edge);
    }

    default int degree(final V vertex) {
        return this.neighbours(vertex).count();
    }

    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof Relationship
                && this.contains((Relationship) object);
    }

    default boolean contains(final Relationship<?, ?> relationship) {
        return this.anyMatch(r -> Objects.equals(r, relationship));
    }

    @Override
    MutableGraph<V, E> mutableCopy();

    @Override
    ImmutableGraph<V, E> immutableCopy();

}
