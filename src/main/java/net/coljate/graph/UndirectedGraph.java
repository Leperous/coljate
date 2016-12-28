package net.coljate.graph;

import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public interface UndirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends UndirectedRelationship<V, E>> iterator();

}
