package net.ollie.coljate.set.mixin;

import net.ollie.coljate.Collection;
import net.ollie.coljate.set.ImmutableSortedSet;
import net.ollie.coljate.set.ImmutableWrappedTreeSet;
import net.ollie.coljate.set.MutableSortedSet;
import net.ollie.coljate.set.MutableWrappedTreeSet;
import net.ollie.coljate.theory.Sorted;

/**
 *
 * @author Ollie
 */
public interface CopiedToTreeSet<T> extends Collection<T>, Sorted<T> {

    @Override
    default MutableSortedSet<T> mutableCopy() {
        return MutableWrappedTreeSet.copyOf(this);
    }

    @Override
    default ImmutableSortedSet<T> immutableCopy() {
        return ImmutableWrappedTreeSet.copyOf(this);
    }

}
