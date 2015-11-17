package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.ImmutableCollection;

/**
 *
 * @author Ollie
 */
public interface ImmutableList<@Nullable T> extends List<T>, ImmutableCollection<T> {

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

    @Override
    ImmutableList<T> with(T element);

    @Override
    ImmutableList<T> tail();

}
