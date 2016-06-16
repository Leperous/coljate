package net.ollie.coljate.list.mixin;

import net.ollie.coljate.list.ImmutableAppendList;
import net.ollie.coljate.list.ImmutableList;
import net.ollie.coljate.list.ImmutablePrefixList;
import net.ollie.coljate.list.MutableList;
import net.ollie.coljate.lists.MutableWrappedArrayList;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Some {@link ImmutableList} that wraps a {@link java.util.List}.
 *
 * @author Ollie
 */
public interface WrapsImmutableList<@Nullable T> extends ImmutableList<T> {

    @Override
    default ImmutableList<T> prefixed(final T element) {
        return ImmutablePrefixList.of(element, this);
    }

    @Override
    default ImmutableList<T> suffixed(final T element) {
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
