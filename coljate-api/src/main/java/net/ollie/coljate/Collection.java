package net.ollie.coljate;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Collection<T> extends Finite<T> {

    boolean contains(Object object);

    @Nonnull
    Collection<T> tail();

    @Nonnull
    MutableCollection<T> mutableCopy();

    @Nonnull
    ImmutableCollection<T> immutableCopy();

}
