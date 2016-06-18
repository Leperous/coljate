package net.ollie.coljate.list.mixin;

import net.ollie.coljate.list.ImmutableArray;
import net.ollie.coljate.list.ImmutableNativeArray;
import net.ollie.coljate.list.MutableArray;
import net.ollie.coljate.list.MutableWrappedArrayList;

/**
 *
 * @author Ollie
 */
public interface CopiedToArray<T> extends CopiedToList<T> {

    @Override
    default MutableArray<T> mutableCopy() {
        return MutableWrappedArrayList.copyOf(this);
    }

    @Override
    default ImmutableArray<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

}
