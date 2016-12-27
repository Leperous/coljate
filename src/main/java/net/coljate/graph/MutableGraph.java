package net.coljate.graph;

import net.coljate.set.MutableSet;

/**
 *
 * @author ollie
 */
public interface MutableGraph<V, E>
        extends Graph<V, E>, MutableSet<Relationship<V, E>> {

    boolean addVertex(V vertex);

    boolean addEdge(V vertex1, V vertex2, E edge);

    boolean removeVertex(Object vertex);

}
