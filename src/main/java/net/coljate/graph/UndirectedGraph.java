package net.coljate.graph;

import net.coljate.util.iterator.CovariantIterator;

/**
 * A graph of relationships, where the vertices are represented by a simple 2-set.
 *
 * @author ollie
 */
public interface UndirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends UndirectedRelationship<V, E>> iterator();


}
