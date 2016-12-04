package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedSet<T>
        extends WrappedSet<T>
        implements ImmutableSet<T> {

    protected ImmutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
