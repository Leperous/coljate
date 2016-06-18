package net.ollie.coljate.set.mixin;

import net.ollie.coljate.set.ImmutableSet;
import net.ollie.coljate.set.ImmutableWrappedHashSet;
import net.ollie.coljate.set.MutableSet;
import net.ollie.coljate.set.MutableWrappedHashSet;
import net.ollie.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public interface CopiedToHashSet<T> extends Set<T> {

    @Override
    default MutableSet<T> mutableCopy() {
        return MutableWrappedHashSet.copyOf(this);
    }

    @Override
    default ImmutableSet<T> immutableCopy() {
        return ImmutableWrappedHashSet.copyOf(this);
    }

}
