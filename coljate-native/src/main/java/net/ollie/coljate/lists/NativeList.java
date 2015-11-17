package net.ollie.coljate.lists;

import java.util.ArrayList;
import java.util.Iterator;
import static java.util.Objects.requireNonNull;
import java.util.OptionalInt;

import net.ollie.coljate.Collection;

/**
 *
 * @author Ollie
 */
public class NativeList<T> implements List<T> {

    public static <T> java.util.ArrayList<T> copyToArrayList(final java.util.Collection<? extends T> collection) {
        return new java.util.ArrayList<>(collection);
    }

    public static <T> java.util.ArrayList<T> copyToArrayList(final Collection<? extends T> collection) {
        final ArrayList<T> list = new java.util.ArrayList<>(collection.size());
        collection.forEach(list::add);
        return list;
    }

    private final java.util.List<T> delegate;

    NativeList(final java.util.List<T> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    protected java.util.ArrayList<T> copyDelegate() {
        return copyToArrayList(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    public T get(int index) {
        return delegate.get(index);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public OptionalInt indexOf(final Object element) {
        final int index = delegate.indexOf(element);
        return index >= 0 ? OptionalInt.of(index) : OptionalInt.empty();
    }

    @Override
    public MutableList<T> mutableCopy() {
        return MutableArrayList.copyOf(delegate);
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return ImmutableArrayList.copyOf(delegate);
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    public List<T> tail() {
        return new NativeList<>(delegate.subList(1, delegate.size() - 1));
    }

}
