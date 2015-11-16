package net.ollie.coljate;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface ImmutableCollection<T> extends Collection<T> {

    @CheckReturnValue
    @Nonnull
    ImmutableCollection<T> with(T element);

    @Override
    ImmutableCollection<T> tail();

    @Override
    @Deprecated
    default ImmutableCollection<T> immutableCopy() {
        return this;
    }

}
