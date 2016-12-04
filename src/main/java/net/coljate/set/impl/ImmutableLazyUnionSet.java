package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class ImmutableLazyUnionSet<T>
        extends LazyUnionSet<T>
        implements ImmutableSet<T> {

    @SuppressWarnings("unchecked")
    public static <T> ImmutableSet<T> viewOf(final ImmutableSet<? extends T> s1, final ImmutableSet<? extends T> s2) {
        if (s1.isEmpty()) {
            return (ImmutableSet<T>) s2;
        } else if (s2.isEmpty()) {
            return (ImmutableSet<T>) s1;
        } else {
            return new ImmutableLazyUnionSet<>(s1, s2);
        }
    }

    protected ImmutableLazyUnionSet(final ImmutableSet<? extends T> s1, ImmutableSet<? extends T> s2) {
        super(s1, s2);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
