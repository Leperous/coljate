package net.coljate.graph.impl;

import java.util.Objects;

import net.coljate.graph.DirectedRelationship;
import net.coljate.graph.MutableDirectedGraph;
import net.coljate.set.MutableSet;
import net.coljate.table.MutableTable;

/**
 *
 * @author ollie
 */
public class MutableTableBackedDirectedGraph<V, E>
        extends TableBackedDirectedGraph<V, E>
        implements MutableDirectedGraph<V, E> {

    public static <V, E> MutableTableBackedDirectedGraph<V, E> createHashBackedGraph() {
        return new MutableTableBackedDirectedGraph<>(MutableSet.createHashSet(), MutableTable.createHashMapBackedTable());
    }

    private final MutableSet<V> vertices;
    private final MutableTable<V, V, E> edges;

    protected MutableTableBackedDirectedGraph(final MutableSet<V> vertices, final MutableTable<V, V, E> edges) {
        super(vertices, edges);
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public boolean addVertex(final V vertex) {
        return vertices.add(vertex);
    }

    @Override
    public boolean addEdge(final V fromVertex, final V toVertex, final E edge) {
        this.addVertex(fromVertex);
        this.addVertex(toVertex);
        return edges.add(fromVertex, toVertex, edge);
    }

    @Override
    public boolean removeVertex(final Object vertex) {
        final boolean removed = vertices.remove(vertex);
        edges.removeWhere(cell -> Objects.equals(cell.rowKey(), vertex) || Objects.equals(cell.columnKey(), vertex));
        return removed;
    }

    @Override
    public boolean add(final DirectedRelationship<V, E> relationship) {
        return this.addEdge(relationship.from(), relationship.to(), relationship.edge());
    }

    @Override
    public boolean remove(final DirectedRelationship<?, ?> relationship) {
        return edges.remove(relationship.from(), relationship.to(), relationship.edge());
    }

    @Override
    public void clear() {
        edges.clear();
    }

}
