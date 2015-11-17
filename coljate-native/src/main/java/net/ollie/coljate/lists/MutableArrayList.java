package net.ollie.coljate.lists;

import java.util.List;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableArrayList<T> extends NativeArrayList<T> implements MutableNativeList<T> {

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableArrayList<>(new java.util.ArrayList<>(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableArrayList<>(list);
    }

    protected MutableArrayList(final java.util.ArrayList<T> delegate) {
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
