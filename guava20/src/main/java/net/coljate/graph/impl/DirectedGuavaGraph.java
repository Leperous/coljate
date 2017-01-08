package net.coljate.graph.impl;

import net.coljate.graph.AbstractGraph;
import net.coljate.graph.AbstractRelationship;
import net.coljate.graph.DirectedGraph;
import net.coljate.graph.DirectedRelationship;
import net.coljate.graph.ImmutableDirectedGraph;
import net.coljate.graph.Relationship;
import net.coljate.set.Set;
import net.coljate.util.iterator.CovariantIterator;
import net.coljate.util.iterator.Iterators;

/**
 *
 * @author Ollie
 */
public class DirectedGuavaGraph<V, E>
        extends AbstractGraph<V, Set<E>>
        implements DirectedGraph<V, Set<E>> {

    private final com.google.common.graph.Network<V, E> graph;
    private Set<V> vertices;

    protected DirectedGuavaGraph(final com.google.common.graph.Network<V, E> graph) {
        if (!graph.isDirected()) {
            throw new IllegalArgumentException("Not a directed graph: " + graph);
        }
        this.graph = graph;
    }

    @Override
    public Set<V> vertices() {
        return vertices == null
                ? vertices = Set.viewOf(graph.nodes())
                : vertices;
    }

    @Override
    public CovariantIterator<Relationship<V, Set<E>>, ? extends DirectedRelationship<V, Set<E>>> iterator() {
        return Iterators.transform(graph.asGraph().edges().iterator(), GuavaRelationship::new);
    }

    @Override
    public ImmutableDirectedGraph<V, Set<E>> immutableCopy() {
        return new ImmutableDirectedGuavaGraph<>(com.google.common.graph.ImmutableNetwork.copyOf(graph));
    }

    public class GuavaRelationship
            extends AbstractRelationship<V, Set<E>>
            implements DirectedRelationship<V, Set<E>> {

        private final com.google.common.graph.EndpointPair<V> endpoints;

        protected GuavaRelationship(final com.google.common.graph.EndpointPair<V> endpoints) {
            this.endpoints = endpoints;
        }

        @Override
        public V from() {
            return endpoints.nodeU();
        }

        @Override
        public V to() {
            return endpoints.nodeV();
        }

        @Override
        public Set<E> edge() {
            return Set.viewOf(graph.edgesConnecting(endpoints.nodeU(), endpoints.nodeV()));
        }

        @Override
        protected boolean equals(final Relationship<?, ?> that) {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
