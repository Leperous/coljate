package net.coljate.graph.impl;

import net.coljate.graph.DirectedRelationship;

/**
 *
 * @author ollie
 */
public class RegularDirectedRelationship<V, E> implements DirectedRelationship<V, E> {

    private final V from, to;
    private final E edge;

    public RegularDirectedRelationship(V from, V to, E edge) {
        this.from = from;
        this.to = to;
        this.edge = edge;
    }

    @Override
    public V from() {
        return from;
    }

    @Override
    public V to() {
        return to;
    }

    @Override
    public E edge() {
        return edge;
    }

}
