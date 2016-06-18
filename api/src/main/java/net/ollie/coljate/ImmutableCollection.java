package net.ollie.coljate;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface ImmutableCollection<@Nullable T> extends Collection<T> {

    @NonNull
    ImmutableCollection<T> with(@Nullable T element);

    @Override
    ImmutableCollection<T> tail();

    @Override
    UnmodifiableIterator<T> iterator();

    @Override
    @Deprecated
    default ImmutableCollection<T> immutableCopy() {
        return this;
    }

}
