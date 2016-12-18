package net.coljate.collection.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.ImmutableCollection;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedCollection<T>
        extends WrappedCollection<T>
        implements ImmutableCollection<T> {

    public static <T> ImmutableCollection<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableWrappedCollection<>(new java.util.ArrayList<>(collection));
    }

    protected ImmutableWrappedCollection(final java.util.Collection<T> delegate) {
        super(delegate);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
