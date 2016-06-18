package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Same as a {@link List}, but with a fixed capacity.
 *
 * Likely backed by an actual object array.
 *
 * @author Ollie
 */
public interface Array<@Nullable T> extends List<T> {

    int capacity();

    @Override
    MutableArray<T> mutableCopy();

    @Override
    ImmutableArray<T> immutableCopy();

}
