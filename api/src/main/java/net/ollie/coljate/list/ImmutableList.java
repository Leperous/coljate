package net.ollie.coljate.list;

import net.ollie.coljate.ImmutableCollection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

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

    @NonNull
    ImmutableList<T> prefixed(T element);

    @NonNull
    ImmutableList<T> suffixed(T element);

    @Override
    ImmutableList<T> tail();

}
