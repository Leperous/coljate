package net.ollie.coljate;

import net.ollie.coljate.theory.Finite;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Collection<T> extends Finite<T> {

    boolean contains(Object object);

    @Override
    Collection<T> tail();

    @Nonnull
    MutableCollection<T> mutableCopy();

    @Nonnull
    ImmutableCollection<T> immutableCopy();

}
