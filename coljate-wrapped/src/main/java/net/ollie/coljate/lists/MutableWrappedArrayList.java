package net.ollie.coljate.lists;

import java.util.List;

import net.ollie.coljate.Collection;
import net.ollie.coljate.lists.mixin.WrapsArrayList;
import static net.ollie.coljate.lists.mixin.WrapsArrayList.copyToArrayList;
import net.ollie.coljate.lists.mixin.WrapsMutableList;

/**
 *
 * @author Ollie
 * @see List
 */
public class MutableWrappedArrayList<T>
        extends WrappedList<T>
        implements WrapsMutableList<T>, WrapsArrayList<T> {

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableWrappedArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> copyOf(final Collection<? extends T> collection) {
        return new MutableWrappedArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableList<T> viewOf(final java.util.ArrayList<T> list) {
        return new MutableWrappedArrayList<>(list);
    }

    private final java.util.ArrayList<T> delegate;

    protected MutableWrappedArrayList(final java.util.ArrayList<T> delegate) {
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

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
