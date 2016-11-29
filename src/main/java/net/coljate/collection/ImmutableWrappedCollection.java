package net.coljate.collection;

import net.coljate.UnmodifiableIterator;

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

    @Override
    public ImmutableCollection<T> with(final T element) {
        final java.util.Collection<T> copy = this.mutableDelegateCopy();
        return copy.add(element)
                ? new ImmutableWrappedCollection<>(copy)
                : this;
    }

}
