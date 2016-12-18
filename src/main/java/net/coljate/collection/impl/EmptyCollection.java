package net.coljate.collection.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.AbstractCollection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public class EmptyCollection<T>
        extends AbstractCollection<T>
        implements ImmutableCollection<T> {

    private static final EmptyCollection EMPTY_COLLECTION = new EmptyCollection();

    @SuppressWarnings("unchecked")
    public static <T> EmptyCollection<T> instance() {
        return EMPTY_COLLECTION;
    }

    @Override
    public MutableCollection<? extends T> mutableCopy() {
        return MutableCollection.copyOf();
    }

    @Override
    @SuppressWarnings("unchecked")
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.of();
    }

    @Override
    protected boolean equals(final AbstractCollection<?> that) {
        return that instanceof EmptyCollection;
    }

    @Override
    public int hashCode() {
        return 123;
    }

}
