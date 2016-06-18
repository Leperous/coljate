package net.ollie.coljate.list.mixin;

import net.ollie.coljate.Collection;
import net.ollie.coljate.list.ImmutableList;
import net.ollie.coljate.list.ImmutableNativeArray;
import net.ollie.coljate.list.MutableWrappedArrayList;
import net.ollie.coljate.list.MutableList;

/**
 *
 * @author Ollie
 */
public interface CopiedToList<T> extends Collection<T> {

    @Override
    default MutableList<T> mutableCopy() {
        return MutableWrappedArrayList.copyOf(this);
    }

    @Override
    default ImmutableList<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

}
