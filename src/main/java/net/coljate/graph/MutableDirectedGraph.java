package net.coljate.graph;

import net.coljate.graph.impl.MutableHashBackedDirectedGraph;

/**
 *
 * @author ollie
 */
public interface MutableDirectedGraph<V, E> extends DirectedGraph<V, E>, MutableGraph<V, E> {

    @Override
    default boolean add(final Relationship<V, E> relationship) {
        return relationship instanceof DirectedRelationship
                && this.addDirected((DirectedRelationship<V, E>) relationship);
    }

    default boolean addDirected(final DirectedRelationship<? extends V, ? extends E> relationship) {
        return this.addEdge(relationship.from(), relationship.to(), relationship.edge());
    }

    @Override
    default boolean remove(final Relationship<?, ?> relationship) {
        return relationship instanceof DirectedRelationship
                && this.removeDirected((DirectedRelationship) relationship);
    }

    boolean removeDirected(DirectedRelationship<?, ?> relationship);

    @Override
    default MutableDirectedGraph<V, E> mutableCopy() {
        return copyOf(this);
    }

    static <V, E> MutableDirectedGraph<V, E> copyOf(final DirectedGraph<V, E> graph) {
        return MutableHashBackedDirectedGraph.copyOf(graph);
    }

    static <V, E> MutableDirectedGraph<V, E> createHashBackedGraph() {
        return MutableHashBackedDirectedGraph.create();
    }

}
