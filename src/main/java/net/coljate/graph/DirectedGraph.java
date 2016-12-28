package net.coljate.graph;

import net.coljate.graph.impl.EmptyDirectedGraph;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 * @see MutableDirectedGraph
 * @see ImmutableDirectedGraph
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends DirectedRelationship<V, E>> iterator();

    @Override
    default MutableDirectedGraph<V, E> mutableCopy() {
        return MutableDirectedGraph.copyOf(this);
    }

    @Override
    ImmutableDirectedGraph<V, E> immutableCopy();

    static <V, E> Graph<V, E> of() {
        return EmptyDirectedGraph.instance();
    }

    static <V, E> Graph<V, E> createHashBackedGraph() {
        return MutableDirectedGraph.createHashBackedGraph();
    }

}
