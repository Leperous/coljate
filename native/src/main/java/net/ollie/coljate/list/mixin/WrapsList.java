package net.ollie.coljate.list.mixin;

import javax.annotation.Nonnull;

import net.ollie.coljate.list.ImmutableList;
import net.ollie.coljate.list.List;
import net.ollie.coljate.list.MutableList;

/**
 * Some {@link List} that wraps a {@link java.util.List}.
 *
 * @author Ollie
 */
public interface WrapsList<T> extends List<T>, CopiedToList<T> {

    @Nonnull
    java.util.List<T> copyDelegate();

    @Override
    default MutableList<T> mutableCopy() {
        return CopiedToList.super.mutableCopy();
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return CopiedToList.super.immutableCopy();
    }

}
