package net.ollie.coljate.lists.mixin;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.lists.ImmutableAppendList;
import net.ollie.coljate.lists.ImmutableList;
import net.ollie.coljate.lists.ImmutablePrefixList;
import net.ollie.coljate.lists.MutableList;
import net.ollie.coljate.lists.MutableWrappedArrayList;

/**
 * Some {@link ImmutableList} that wraps a {@link java.util.List}.
 *
 * @author Ollie
 */
public interface WrapsImmutableList<@Nullable T> extends ImmutableList<T> {

    @Override
    default ImmutableList<T> prefix(final T element) {
        return ImmutablePrefixList.of(element, this);
    }

    @Override
    default ImmutableList<T> suffix(final T element) {
        return ImmutableAppendList.of(this, element);
    }

    @Override
    default MutableList<T> mutableCopy() {
        return MutableWrappedArrayList.copyOf(this);
    }

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

}
