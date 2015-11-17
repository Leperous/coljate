package net.ollie.coljate;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.Container;
import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 */
public interface Collection<@Nullable T> extends Container, Finite<T> {

    @Override
    boolean contains(Object object);

    @Override
    Collection<T> tail();

    @NonNull
    MutableCollection<T> mutableCopy();

    @NonNull
    ImmutableCollection<T> immutableCopy();

}
