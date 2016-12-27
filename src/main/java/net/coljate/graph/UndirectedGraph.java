package net.coljate.graph;

import net.coljate.util.iterator.CovariantIterator;

/**
 * @author ollie
 */
public interface UndirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends UndirectedRelationship<V, E>> iterator();

    interface UndirectedRelationship<V, E> extends Relationship<V, E> {

        @Override
        default boolean isBetween(final Object fromVertex, final Object toVertex) {
            return this.vertices().contains(fromVertex, toVertex);
        }

    }

}
