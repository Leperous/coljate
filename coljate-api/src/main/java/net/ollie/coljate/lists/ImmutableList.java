package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.ImmutableCollection;

/**
 *
 * @author Ollie
 * @param <T>
 * @see MutableList
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
        return this.suffixed(element);
    }

    ImmutableList<T> prefixed(T element);

    ImmutableList<T> suffixed(T element);

    @Override
    ImmutableList<T> tail();

}
