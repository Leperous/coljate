package net.ollie.coljate.set;

import javax.annotation.Nonnull;

import net.ollie.coljate.theory.Sorted;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface SortedSet<@Nullable T> extends Set<T>, Sorted<T> {

    T min();

    T max();

    @Nonnull
    SortedSet<T> subSet(T min, T max);

    @Override
    SortedSet<T> tail();

    @Override
    MutableSortedSet<T> mutableCopy();

    @Override
    ImmutableSortedSet<T> immutableCopy();

}
