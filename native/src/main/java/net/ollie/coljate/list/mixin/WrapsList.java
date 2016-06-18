package net.ollie.coljate.list.mixin;

import javax.annotation.Nonnull;

import net.ollie.coljate.list.List;

/**
 * Some {@link List} that wraps a {@link java.util.List}.
 *
 * @author Ollie
 */
public interface WrapsList<T> extends List<T> {

    @Nonnull
    java.util.List<T> copyDelegate();

}
