package net.ollie.coljate.lists.mixin;

import javax.annotation.Nonnull;

import net.ollie.coljate.lists.List;

/**
 *
 * @author Ollie
 */
public interface WrapsList<T> extends List<T> {

    @Nonnull
    java.util.List<T> copyDelegate();

}
