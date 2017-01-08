package net.coljate.graph.impl;

import net.coljate.graph.DirectedRelationship;
import net.coljate.graph.ImmutableDirectedGraph;
import net.coljate.graph.Relationship;
import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;
import net.coljate.util.iterator.UnmodifiableCovariantIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableDirectedGuavaGraph<V, E>
        extends DirectedGuavaGraph<V, E>
        implements ImmutableDirectedGraph<V, Set<E>> {

    protected ImmutableDirectedGuavaGraph(final com.google.common.graph.ImmutableNetwork<V, E> graph) {
        super(graph);
    }

    @Override
    public ImmutableSet<V> vertices() {
        return super.vertices().immutableCopy();
    }

    @Override
    public UnmodifiableCovariantIterator<Relationship<V, Set<E>>, ? extends DirectedRelationship<V, Set<E>>> iterator() {
        return UnmodifiableCovariantIterator.wrap(super.iterator());
    }

}
