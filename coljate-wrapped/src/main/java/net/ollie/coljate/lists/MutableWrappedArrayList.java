package net.ollie.coljate.lists;


import java.util.List;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableWrappedArrayList<T> extends WrappedArrayList<T> implements MutableWrappedList<T> {

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableWrappedArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> copyOf(final Collection<? extends T> collection) {
        return new MutableWrappedArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableWrappedArrayList<>(list);
    }

    protected MutableWrappedArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
    }

    @Override
    public java.util.List<T> delegate() {
        return delegate;
    }

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
