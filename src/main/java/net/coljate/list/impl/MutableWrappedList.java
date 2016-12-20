package net.coljate.list.impl;

import net.coljate.collection.impl.MutableWrappedCollection;
import net.coljate.list.ImmutableList;
import net.coljate.list.ListIterator;
import net.coljate.list.MutableList;

/**
 *
 * @author ollie
 */
public class MutableWrappedList<T>
        extends MutableWrappedCollection<T>
        implements MutableList<T> {

    @SafeVarargs
    public static <T> MutableWrappedList<T> createArrayList(final T... elements) {
        final java.util.ArrayList<T> list = new java.util.ArrayList<>(elements.length);
        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }
        return new MutableWrappedList<>(list);
    }

    private final java.util.List<T> delegate;

    public MutableWrappedList(final java.util.List<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.List<T> mutableJavaCopy() {
        return new java.util.ArrayList<>(delegate);
    }

    @Override
    public void prefix(final T element) {
        delegate.add(0, element);
    }

    @Override
    public void suffix(final T element) {
        delegate.add(element);
    }

    @Override
    public T last() {
        return delegate.get(delegate.size() - 1);
    }

    @Override
    public ListIterator<T> iterator() {
        return new WrappedListIterator();
    }

    @Override
    public MutableList<T> mutableCopy() {
        return new MutableWrappedList<>(this.mutableJavaCopy());
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return MutableList.super.immutableCopy();
    }

    private final class WrappedListIterator implements ListIterator<T> {

        private final java.util.ListIterator<T> delegate = MutableWrappedList.this.delegate.listIterator();

        @Override
        public boolean hasNext() {
            return delegate.hasNext();
        }

        @Override
        public T next() {
            return delegate.next();
        }

        @Override
        public boolean hasPrevious() {
            return delegate.hasPrevious();
        }

        @Override
        public T previous() {
            return delegate.previous();
        }

    }

}
