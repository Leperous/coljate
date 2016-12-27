package net.coljate.graph;

import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface ImmutableGraph<V, E>
        extends Graph<V, E>, ImmutableSet<Relationship<V, E>> {

    @Override
    @Deprecated
    default ImmutableGraph<V, E> immutableCopy() {
        return this;
    }

}
