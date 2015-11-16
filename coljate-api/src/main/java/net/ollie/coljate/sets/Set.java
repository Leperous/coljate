package net.ollie.coljate.sets;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public interface Set<T> extends Collection<T> {

    @Override
    Set<T> tail();

    @Override
    MutableSet<T> mutableCopy();

    @Override
    ImmutableSet<T> immutableCopy();

}
