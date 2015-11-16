package net.ollie.coljate.sets;

import net.ollie.coljate.ImmutableCollection;

/**
 *
 * @author Ollie
 */
public interface ImmutableSet<T> extends Set<T>, ImmutableCollection<T> {

    @Override
    ImmutableSet<T> tail();

    @Override
    @Deprecated
    default ImmutableSet<T> immutableCopy() {
        return this;
    }

}
