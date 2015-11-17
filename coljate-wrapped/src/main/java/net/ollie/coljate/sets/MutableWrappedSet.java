package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
public class MutableWrappedSet<T> extends WrappedSet<T> implements MutableSet<T> {

    public MutableWrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
    }

    @Override
    public boolean add(final T element) {
        return delegate.add(element);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean remove(final Object element) {
        return delegate.remove(element);
    }

}
