package net.coljate;

import java.util.Collection;

/**
 *
 * @author ollie
 */
public class ImmutableWrappedCollection<T>
        extends WrappedCollection<T>
        implements ImmutableCollection<T> {

    protected ImmutableWrappedCollection(final Collection<T> delegate) {
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
