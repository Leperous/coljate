package net.ollie.coljate.set;

import net.ollie.coljate.ImmutableCollection;

/**
 *
 * @author Ollie
 */
public interface ImmutableSet<T> extends Set<T>, ImmutableCollection<T> {

    @Override
    ImmutableSet<T> tail();

    @Override
    ImmutableSet<T> with(T element);

    @Override
    @Deprecated
    default ImmutableSet<T> immutableCopy() {
        return this;
    }

}
