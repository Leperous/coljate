package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface WrappedImmutableList<@Nullable T> extends ImmutableList<T> {

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
