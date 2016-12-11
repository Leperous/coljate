package net.coljate.set.impl;

import java.util.Collections;

import net.coljate.set.ConcurrentSet;

/**
 *
 * @author ollie
 */
public class ConcurrentWrappedSet<T>
        extends MutableWrappedSet<T>
        implements ConcurrentSet<T> {

    public static <T> ConcurrentWrappedSet<T> createHashSet(final int initialCapacity) {
        return new ConcurrentWrappedSet<>(createConcurrentHashSet(initialCapacity));
    }

    public static <T> ConcurrentWrappedSet<T> copyIntoHashSet(final java.util.Collection<? extends T> collection) {
        final java.util.Set<T> set = createConcurrentHashSet(collection.size());
        collection.forEach(set::add);
        return new ConcurrentWrappedSet<>(set);
    }

    private static <T> java.util.Set<T> createConcurrentHashSet(final int initialCapacity) {
        return Collections.newSetFromMap(new java.util.concurrent.ConcurrentHashMap<>(initialCapacity));
    }

    protected ConcurrentWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

}
