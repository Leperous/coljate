package net.ollie.coljate.list;

import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.list.mixin.WrapsArrayList;
import static net.ollie.coljate.list.mixin.WrapsArrayList.copyToArrayList;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedArrayList<T>
        extends WrappedList<T>
        implements ImmutableArray<T>, WrapsArrayList<T> {

    public static <T> ImmutableArray<T> of() {
        return ImmutableWrappedEmptyArray.empty();
    }

    public static <T> ImmutableArray<T> of(final T element) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(1);
        list.add(element);
        return new ImmutableWrappedArrayList<>(list);
    }

    @SafeVarargs
    public static <T> ImmutableArray<T> copyOf(final T... array) {
        switch (array.length) {
            case 0:
                return of();
            case 1:
                return of(array[0]);
            default:
                return new ImmutableWrappedArrayList<>(copyToArrayList(array));
        }
    }

    public static <T> ImmutableArray<T> copyOf(final java.util.Collection<? extends T> collection) {
        switch (collection.size()) {
            case 0:
                return of();
            default:
                return new ImmutableWrappedArrayList<>(copyToArrayList(collection));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ImmutableArray<T> copyOf(final Collection<? extends T> collection) {
        if (collection instanceof ImmutableArray) {
            return (ImmutableArray<T>) collection;
        }
        switch (collection.count()) {
            case 0:
                return of();
            case 1:
                return of(collection.head());
            default:
                return new ImmutableWrappedArrayList<>(copyToArrayList(collection));
        }
    }

    private final java.util.ArrayList<T> delegate;

    ImmutableWrappedArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.ArrayList<T> copyDelegate() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public ImmutableList<T> prefixed(final T element) {
        final java.util.ArrayList<T> copy = this.copyDelegate();
        copy.add(0, element);
        return new ImmutableWrappedArrayList<>(copy);
    }

    @Override
    public ImmutableList<T> suffixed(final T element) {
        final java.util.ArrayList<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableWrappedArrayList<>(copy);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new DelegatedUnmodifiableIterator<>(super.iterator());
    }

    @Override
    public ImmutableArray<T> tail() {
        return ImmutableWrappedArrayList.copyOf(super.tail());
    }

    @Override
    @Deprecated
    public ImmutableWrappedArrayList<T> immutableCopy() {
        return this;
    }

    @Override
    public MutableArray<T> mutableCopy() {
        return WrapsArrayList.super.mutableCopy();
    }

}
