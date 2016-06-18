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
public class MutableWrappedArrayList<T>
        extends MutableWrappedList<T>
        implements MutableArray<T>, WrapsArrayList<T> {

    @SafeVarargs
    public static <T> MutableList<T> copyOf(final T... array) {
        return new MutableWrappedArrayList<>(copyToArrayList(array));
    }

    public static <T> MutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new MutableWrappedArrayList<>(copyToArrayList(collection));
    }

    public static <T> MutableArray<T> copyOf(final Collection<? extends T> collection) {
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
    public void setCapacity(int capacity) {
        delegate.ensureCapacity(capacity);
    }

    @Override
    public MutableArray<T> mutableCopy() {
        return WrapsArrayList.super.mutableCopy();
    }

    @Override
    public ImmutableArray<T> immutableCopy() {
        return WrapsArrayList.super.immutableCopy();
    }

}
