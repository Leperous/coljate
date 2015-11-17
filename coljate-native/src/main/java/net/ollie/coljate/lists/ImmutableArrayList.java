package net.ollie.coljate.lists;

import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableArrayList<T> extends NativeArrayList<T> implements ImmutableList<T> {

    public static <T> ImmutableList<T> of() {
        return ImmutableEmptyList.instance();
    }

    public static <T> ImmutableList<T> of(final T element) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(1);
        list.add(element);
        return new ImmutableArrayList<>(list);
    }

    @SafeVarargs
    public static <T> ImmutableList<T> copyOf(final T... array) {
        switch (array.length) {
            case 0:
                return of();
            case 1:
                return of(array[0]);
            default:
                return new ImmutableArrayList<>(copyToArrayList(array));
        }
    }

    public static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        switch (collection.size()) {
            case 0:
                return of();
            default:
                return new ImmutableArrayList<>(copyToArrayList(collection));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ImmutableList<T> copyOf(final Collection<? extends T> collection) {
        if (collection instanceof ImmutableList) {
            return (ImmutableList<T>) collection;
        }
        switch (collection.size()) {
            case 0:
                return of();
            case 1:
                return of(collection.head());
            default:
                return new ImmutableArrayList<>(copyToArrayList(collection));
        }
    }

    ImmutableArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
    }

    @Override
    public ImmutableList<T> with(final T element) {
        final java.util.ArrayList<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableArrayList<>(copy);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new DelegatedUnmodifiableIterator<>(super.iterator());
    }

    @Override
    public ImmutableList<T> tail() {
        return ImmutableArrayList.copyOf(super.tail());
    }

    @Override
    @Deprecated
    public ImmutableArrayList<T> immutableCopy() {
        return this;
    }

}
