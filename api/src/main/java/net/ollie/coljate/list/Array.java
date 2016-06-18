package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.feature.ConstantGet;

/**
 * Same as a {@link List}, but with a fixed capacity.
 *
 * Likely backed by an actual object array.
 *
 * @author Ollie
 */
public interface Array<@Nullable T> extends List<T>, ConstantGet<Integer, T> {

    int capacity();

    @Override
    MutableArray<T> mutableCopy();

    @Override
    ImmutableArray<T> immutableCopy();

}
