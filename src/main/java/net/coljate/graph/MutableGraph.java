package net.coljate.graph;

/**
 *
 * @author ollie
 */
public interface MutableGraph<V, E> extends Graph<V, E> {

    boolean addVertex(V vertex);

    boolean addEdge(V vertex1, V vertex2, E edge);

    boolean removeVertex(Object vertex);

}
