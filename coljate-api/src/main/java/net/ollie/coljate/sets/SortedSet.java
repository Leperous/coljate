package net.ollie.coljate.sets;

import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.Sorted;

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
    MutableSortedSet<T> mutableCopy();

    @Override
    ImmutableSortedSet<T> immutableCopy();

}
