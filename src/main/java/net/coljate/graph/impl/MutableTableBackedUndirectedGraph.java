package net.coljate.graph.impl;

import net.coljate.graph.MutableUndirectedGraph;
import net.coljate.graph.Relationship;
import net.coljate.set.MutableSet;
import net.coljate.table.impl.MutableSymmetricTable;

/**
 *
 * @author ollie
 */
public class MutableTableBackedUndirectedGraph<V, E>
        extends TableBackedUndirectedGraph<V, E>
        implements MutableUndirectedGraph<V, E> {

    private final MutableSet<V> vertices;
    private final MutableSymmetricTable<V, E> edges;

    protected MutableTableBackedUndirectedGraph(final MutableSet<V> vertices, final MutableSymmetricTable<V, E> edges) {
        super(vertices, edges);
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public boolean addVertex(V vertex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addEdge(V fromVertex, V toVertex, E edge) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeVertex(Object vertex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Relationship<?, ?> relationship) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(Relationship<V, E> element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
