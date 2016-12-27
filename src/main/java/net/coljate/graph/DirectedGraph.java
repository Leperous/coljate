package net.coljate.graph;

import java.util.Objects;
import java.util.Optional;

import net.coljate.set.impl.TwoSet;
import net.coljate.util.iterator.CovariantIterator;

/**
 *
 * @author ollie
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {

    @Override
    CovariantIterator<Relationship<V, E>, ? extends DirectedRelationship<V, E>> iterator();


}
