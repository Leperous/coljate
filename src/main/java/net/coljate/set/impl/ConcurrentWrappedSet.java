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
        return new ConcurrentWrappedSet<>(Collections.newSetFromMap(new java.util.concurrent.ConcurrentHashMap<>(initialCapacity)));
    }

    protected ConcurrentWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

}
