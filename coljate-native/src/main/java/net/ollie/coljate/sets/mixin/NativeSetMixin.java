package net.ollie.coljate.sets.mixin;

import net.ollie.coljate.sets.ImmutableHashSet;
import net.ollie.coljate.sets.ImmutableSet;
import net.ollie.coljate.sets.MutableHashSet;
import net.ollie.coljate.sets.MutableSet;
import net.ollie.coljate.sets.Set;

/**
 *
 * @author Ollie
 */
public interface NativeSetMixin<T> extends Set<T> {

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableHashSet.copyOf(this);
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return ImmutableHashSet.copyOf(this);
    }

}
