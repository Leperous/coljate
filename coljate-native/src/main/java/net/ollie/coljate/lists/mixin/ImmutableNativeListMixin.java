package net.ollie.coljate.lists.mixin;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.lists.ImmutableAppendList;
import net.ollie.coljate.lists.ImmutableList;

/**
 *
 * @author Ollie
 */
public interface ImmutableNativeListMixin<@Nullable T> extends ImmutableList<T>, NativeListMixin<T> {

    @Override
    default ImmutableList<T> with(final T element) {
        return ImmutableAppendList.of(this, element);
    }

    @Override
    @Deprecated
    default ImmutableList<T> immutableCopy() {
        return this;
    }

}
