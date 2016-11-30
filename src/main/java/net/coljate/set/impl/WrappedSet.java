package net.coljate.set.impl;

import net.coljate.collection.WrappedCollection;
import net.coljate.set.ImmutableSet;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class WrappedSet<T> extends WrappedCollection<T> implements Set<T> {

    public static <T> Set<T> viewOf(final java.util.Set<T> set) {
        return new WrappedSet<>(set);
    }

    private final java.util.Set<T> delegate;

    protected WrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.Set<T> mutableJavaCopy() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return new MutableWrappedSet<>(this.mutableJavaCopy());
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
