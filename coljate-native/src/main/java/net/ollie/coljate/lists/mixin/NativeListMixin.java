package net.ollie.coljate.lists.mixin;

import net.ollie.coljate.lists.ImmutableArrayList;
import net.ollie.coljate.lists.ImmutableList;
import net.ollie.coljate.lists.List;
import net.ollie.coljate.lists.MutableArrayList;
import net.ollie.coljate.lists.MutableList;

/**
 *
 * @author Ollie
 */
public interface NativeListMixin<T> extends List<T> {

    @Override
    default MutableList<T> mutableCopy() {
        return MutableArrayList.copyOf(this);
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableArrayList.copyOf(this);
    }

}
