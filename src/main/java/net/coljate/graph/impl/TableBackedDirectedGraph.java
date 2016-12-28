package net.coljate.graph.impl;

import net.coljate.graph.AbstractGraph;
import net.coljate.graph.DirectedGraph;
import net.coljate.graph.DirectedRelationship;
import net.coljate.graph.ImmutableGraph;
import net.coljate.graph.MutableDirectedGraph;
import net.coljate.graph.Relationship;
import net.coljate.set.Set;
import net.coljate.set.impl.TwoSet;
import net.coljate.table.Cell;
import net.coljate.table.Table;
import net.coljate.util.Functions;
import net.coljate.util.iterator.CovariantIterator;
import net.coljate.util.iterator.Iterators;

/**
 * A graph backed by a {@link Table}, where rows represent "from" vertices and columns "to" vertices.
 *
 * The inverse relationship is not managed by this class, so should be explicitly entered into the table.
 *
 * @author ollie
 */
public class TableBackedDirectedGraph<V, E>
        extends AbstractGraph<V, E>
        implements DirectedGraph<V, E> {

    private final Set<V> vertices;
    private final Table<V, V, E> edges;

    protected TableBackedDirectedGraph(final Set<V> vertices, final Table<V, V, E> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public Set<V> vertices() {
        return vertices;
    }

    @Override
    public E edgeBetween(final Object fromVertex, final Object toVertex) {
        return edges.getIfPresent(fromVertex, toVertex);
    }

    @Override
    public Relationship<V, E> relationshipBetween(final Object fromVertex, final Object toVertex) {
        return Functions.ifNonNull(edges.cellIfPresent(fromVertex, toVertex), this::toRelationship);
    }

    @Override
    public CovariantIterator<Relationship<V, E>, ? extends DirectedRelationship<V, E>> iterator() {
        return Iterators.transform(edges.iterator(), this::toRelationship);
    }

    protected CellBackedRelationship toRelationship(final Cell<V, V, E> cell) {
        return new CellBackedRelationship(cell);
    }

    @Override
    public MutableDirectedGraph<V, E> mutableCopy() {
        return new MutableHashBackedDirectedGraph<>(vertices.mutableCopy(), edges.mutableCopy());
    }

    @Override
    public ImmutableGraph<V, E> immutableCopy() {
        return new ImmutableTableBackedDirectedGraph<>(vertices.immutableCopy(), edges.immutableCopy());
    }

    protected class CellBackedRelationship implements DirectedRelationship<V, E> {

        private final Cell<V, V, E> cell;
        private TwoSet<V> vertices;

        CellBackedRelationship(final Cell<V, V, E> cell) {
            this.cell = cell;
        }

        @Override
        public V from() {
            return cell.rowKey();
        }

        @Override
        public V to() {
            return cell.columnKey();
        }

        @Override
        public TwoSet<V> vertices() {
            return vertices == null
                    ? vertices = TwoSet.require(cell.rowKey(), cell.columnKey())
                    : vertices;
        }

        @Override
        public E edge() {
            return cell.value();
        }

    }

}
