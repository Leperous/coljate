package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.ImmutableCollection;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface ImmutableList<@Nullable T>
        extends List<T>, ImmutableCollection<T> {

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

    @Override
    @Deprecated
    default ImmutableCollection<T> with(final T element) {
        return this.suffix(element);
    }

    ImmutableList<T> prefix(T element);

    ImmutableList<T> suffix(T element);

    @Override
    ImmutableList<T> tail();

}
