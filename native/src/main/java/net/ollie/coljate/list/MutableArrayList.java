package net.ollie.coljate.list;

import java.util.List;

import net.ollie.coljate.Collection;
import net.ollie.coljate.list.mixin.WrapsArrayList;
import static net.ollie.coljate.list.mixin.WrapsArrayList.copyToArrayList;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableArrayList<T>
        extends MutableWrappedList<T>
        implements WrapsArrayList<T> {

    @SafeVarargs
    public static <T> MutableList<T> copyOf(final T... array) {
        return new MutableArrayList<>(copyToArrayList(array));
    }

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> copyOf(final Collection<? extends T> collection) {
        return new MutableArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableArrayList<>(list);
    }

    private final java.util.ArrayList<T> delegate;

    protected MutableArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.ArrayList<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.ArrayList<T> copyDelegate() {
        return new java.util.ArrayList<>(delegate);
    }

}
