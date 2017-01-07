package net.coljate.graph;

/**
 *
 * @author ollie
 */
public interface UndirectedRelationship<V, E> extends Relationship<V, E> {

    @Override
    default boolean isBetween(final Object fromVertex, final Object toVertex) {
        return this.vertices().contains(fromVertex, toVertex);
    }

}
