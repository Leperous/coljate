package net.coljate.set;

import net.coljate.collection.ConcurrentCollection;
import net.coljate.set.impl.ConcurrentWrappedSet;

/**
 *
 * @author ollie
 */
public interface ConcurrentSet<T>
        extends MutableSet<T>, ConcurrentCollection<T> {

    @Override
    ConcurrentSet<T> mutableCopy();

    static <T> ConcurrentSet<T> createHashSet() {
        return ConcurrentWrappedSet.createHashSet();
    }

    static <T> ConcurrentSet<T> createHashSet(final int initialCapacity) {
        return ConcurrentWrappedSet.createHashSet(initialCapacity);
    }

}
