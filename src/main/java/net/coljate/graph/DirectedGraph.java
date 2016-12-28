package net.coljate.graph;

import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends DirectedRelationship<V, E>> iterator();

    @Override
    MutableDirectedGraph<V, E> mutableCopy();

}
