package net.coljate.list;

import net.coljate.WrappedCollection;

/**
 *
 * @author ollie
 */
public class WrappedList<T> extends WrappedCollection<T> implements List<T> {

    public static <T> List<T> viewOf(final java.util.List<T> collection) {
        return new WrappedList<>(collection);
    }

    public static <T> List<T> copyOf(final java.util.Collection<T> collection) {
        return viewOf(new java.util.ArrayList<>(collection));
    }

    private final java.util.List<T> delegate;

    protected WrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
