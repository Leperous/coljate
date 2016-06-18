package net.ollie.coljate.set.mixin;

import net.ollie.coljate.set.ImmutableSortedSet;
import net.ollie.coljate.set.MutableSortedSet;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.set.SortedSet;

/**
 * Some {@link Set} that wraps a {@link java.util.TreeSet}.
 *
 * @author Ollie
 */
public interface WrapsTreeSet<T> extends WrapsSet<T>, SortedSet<T>, CopiedToTreeSet<T> {

    @Override
    java.util.TreeSet<T> copyDelegate();

    @Override
    default ImmutableSortedSet<T> immutableCopy() {
        return CopiedToTreeSet.super.immutableCopy();
    }

    @Override
    default MutableSortedSet<T> mutableCopy() {
        return CopiedToTreeSet.super.mutableCopy();
    }

}
