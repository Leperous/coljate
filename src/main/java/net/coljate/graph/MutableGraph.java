package net.coljate.graph;

import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public interface MutableGraph<V, E>
        extends Graph<V, E>, MutableSet<Relationship<V, E>> {

    boolean addVertex(V vertex);

    boolean addEdge(V fromVertex, V toVertex, E edge);

    boolean removeVertex(Object vertex);

    @Deprecated
    @Override
    default boolean remove(final Object object) {
        return object instanceof Relationship
                && this.remove((Relationship<?, ?>) object);
    }

    boolean remove(Relationship<?, ?> relationship);

}
