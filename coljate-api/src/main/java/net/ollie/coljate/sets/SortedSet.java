package net.ollie.coljate.sets;

import net.ollie.coljate.theory.Sorted;

/**
 *
 * @author Ollie
 */
public interface SortedSet<T> extends Set<T>, Sorted<T> {

    @Override
    MutableSortedSet<T> mutableCopy();

    @Override
    ImmutableSortedSet<T> immutableCopy();

}
