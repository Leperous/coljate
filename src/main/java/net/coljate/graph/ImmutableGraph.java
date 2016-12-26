package net.coljate.graph;

import net.coljate.set.ImmutableSet;
import net.coljate.graph.Graph.Relationship;

/**
 *
 * @author ollie
 */
public interface ImmutableGraph<X, V, E>
        extends Graph<X, V, E>, ImmutableSet<Relationship<X, E>> {

    @Override
    @Deprecated
    default ImmutableGraph<X, V, E> immutableCopy() {
        return this;
    }

}
