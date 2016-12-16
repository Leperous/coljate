package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.Collection;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedSet<T>
        extends WrappedSet<T>
        implements ImmutableSet<T> {

    public static <T> ImmutableWrappedSet<T> copyIntoHashSet(final Collection<? extends T> collection) {
        final java.util.Set<T> set = collection.mutableJavaCopy(i -> new java.util.HashSet<>(i));
        return new ImmutableWrappedSet<>(set);
    }

    protected ImmutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
