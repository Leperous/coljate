package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.theory.feature.ConstantGet;

/**
 * A {@link List} implementation, where lookup takes constant-time, and with a {@link #capacity() maximum size}.
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
