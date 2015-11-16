package net.ollie.coljate.sets;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public interface Set<@Nullable T> extends Collection<T> {

    @Override
    Set<T> tail();

    @Override
    MutableSet<T> mutableCopy();

    @Override
    ImmutableSet<T> immutableCopy();

}
