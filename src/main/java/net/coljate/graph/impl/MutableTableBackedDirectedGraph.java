package net.coljate.graph.impl;

import java.util.Objects;

import net.coljate.graph.DirectedRelationship;
import net.coljate.graph.MutableGraph;
import net.coljate.graph.Relationship;
import net.coljate.set.MutableSet;
import net.coljate.table.MutableTable;

/**
 *
 * @author ollie
 */
public class MutableTableBackedDirectedGraph<V, E>
        extends TableBackedDirectedGraph<V, E>
        implements MutableGraph<V, E> {

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
    public boolean addEdge(final V vertex1, final V vertex2, final E edge) {
        vertices.add(vertex1);
        vertices.add(vertex2);
        return edges.add(vertex1, vertex2, edge);
    }

    @Override
    public boolean removeVertex(final Object vertex) {
        final boolean removed = vertices.remove(vertex);
        edges.removeWhere(cell -> Objects.equals(cell.rowKey(), vertex) || Objects.equals(cell.columnKey(), vertex));
        return removed;
    }

    @Override
    public boolean add(final Relationship<V, E> relationship) {
        return relationship instanceof DirectedRelationship
                && this.add((DirectedRelationship<V, E>) relationship);
    }

    public boolean add(final DirectedRelationship<V, E> relationship) {
        return this.addEdge(relationship.from(), relationship.to(), relationship.edge());
    }

    @Override
    public boolean remove(final Relationship<?, ?> relationship) {
        return relationship instanceof DirectedRelationship
                && this.remove((DirectedRelationship) relationship);
    }

    public boolean remove(final DirectedRelationship<?, ?> relationship) {
        return edges.remove(relationship.from(), relationship.to(), relationship.edge());
    }

    @Override
    public void clear() {
        edges.clear();
    }

}
