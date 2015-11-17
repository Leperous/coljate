package net.ollie.coljate.lists.mixin;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.lists.ImmutableAppendList;
import net.ollie.coljate.lists.ImmutableList;
import net.ollie.coljate.lists.MutableList;
import net.ollie.coljate.lists.MutableWrappedArrayList;

/**
 *
 * @author Ollie
 */
public interface WrapsImmutableList<@Nullable T> extends ImmutableList<T>, WrapsList<T> {

    @Override
    default ImmutableList<T> with(final T element) {
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
