package net.coljate.set;

import net.coljate.collection.Collection;
import net.coljate.set.impl.WrappedSet;

/**
 *
 * @author ollie
 */
public interface Set<T> extends Collection<T> {

    @Override
    MutableSet<T> mutableCopy();

    @Override
    ImmutableSet<T> immutableCopy();

    @Override
    default java.util.Set<T> mutableJavaCopy() {
        return this.mutableJavaCopy(java.util.HashSet::new);
    }

    static <T> Set<T> viewOf(final java.util.Set<T> set) {
        return WrappedSet.viewOf(set);
    }

}
