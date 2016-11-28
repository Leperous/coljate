package net.coljate.set;

import net.coljate.set.impl.ConcurrentWrappedSet;

/**
 *
 * @author ollie
 */
public interface ConcurrentSet<T> extends MutableSet<T> {

    static <T> ConcurrentSet<T> createHashSet(final int initialCapacity) {
        return ConcurrentWrappedSet.createHashSet(initialCapacity);
    }

}
