package net.ollie.coljate.theory;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface Finite<@Nullable T> extends Traversable<T> {

    int size();

    @Override
    Finite<T> tail();

}
