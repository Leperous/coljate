package net.ollie.coljate.theory;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface Finite<@Nullable T> extends Traversable<T> {

    /**
     *
     * @return the number of elements in this collection.
     */
    int count();

    @Override
    Finite<T> tail();

}
