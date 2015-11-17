package net.ollie.coljate.lists.mixin;

import net.ollie.coljate.lists.ImmutableAppendList;
import net.ollie.coljate.lists.ImmutableList;

/**
 *
 * @author Ollie
 */
public interface ImmutableNativeListMixin<T> extends ImmutableList<T>, NativeListMixin<T> {

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
