package net.ollie.coljate.list.mixin;

import net.ollie.coljate.Collection;
import net.ollie.coljate.ImmutableCollection;
import net.ollie.coljate.MutableCollection;
import net.ollie.coljate.list.ImmutableNativeArray;
import net.ollie.coljate.list.MutableWrappedArrayList;

/**
 *
 * @author Ollie
 */
public interface CopiedToCollection<T> extends Collection<T> {

    @Override
    default MutableCollection<T> mutableCopy() {
        return MutableWrappedArrayList.copyOf(this);
    }

    @Override
    default ImmutableCollection<T> immutableCopy() {
        return ImmutableNativeArray.copyOf(this);
    }

}
