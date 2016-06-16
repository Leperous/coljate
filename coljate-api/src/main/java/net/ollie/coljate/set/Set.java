package net.ollie.coljate.set;

import net.ollie.coljate.Collection;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface Set<@Nullable T>
        extends Collection<T> {

    @Override
    Set<T> tail();

    @Override
    MutableSet<T> mutableCopy();

    @Override
    ImmutableSet<T> immutableCopy();

}
