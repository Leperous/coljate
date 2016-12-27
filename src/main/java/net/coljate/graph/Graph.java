package net.coljate.graph;

import net.coljate.map.Map;
import net.coljate.set.Set;

/**
 * A data structure where pairs of objects are "related" somehow.
 *
 * @author ollie
 * @param <V> vertex type
 * @param <E> edge type
 */
public interface Graph<V, E> {

    Set<V> vertices();

    Set<E> edges();

    Map<V, E> neighbours(V vertex);

    default E edgeBetween(final V vertex1, final V vertex2) {
        return this.neighbours(vertex1).get(vertex2);
    }

    default int order() {
        return this.vertices().count();
    }

    default int size() {
        return this.edges().count();
    }

    default int degree(final V vertex) {
        return this.neighbours(vertex).count();
    }
    
    MutableGraph<V, E> mutableCopy();

}
