package net.ollie.coljate;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.theory.Finite;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface Collection<@Nullable T> extends Finite<T> {

    boolean contains(Object object);

    @Override
    Collection<T> tail();

    @NonNull
    MutableCollection<T> mutableCopy();

    @NonNull
    ImmutableCollection<T> immutableCopy();

}
