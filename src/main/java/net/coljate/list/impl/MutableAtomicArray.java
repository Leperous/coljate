package net.coljate.list.impl;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Function;

import net.coljate.list.ConcurrentArray;
import net.coljate.list.ImmutableArray;
import net.coljate.list.ListIterator;

/**
 *
 * @author ollie
 */
public class MutableAtomicArray<T> implements ConcurrentArray<T> {

    private final AtomicReference<AtomicReferenceArray<T>> arrayRef;

    protected MutableAtomicArray(final AtomicReferenceArray<T> array) {
        this.arrayRef = new AtomicReference<>(array);
    }

    private AtomicReferenceArray<T> array() {
        return arrayRef.get();
    }

    private static <T> AtomicReferenceArray<T> copy(final AtomicReferenceArray<T> current) {
        final AtomicReferenceArray<T> copy = new AtomicReferenceArray<>(current.length());
        for (int i = 0; i < current.length(); i++) {
            copy.set(i, current.get(i));
        }
        return copy;
    }

    private AtomicReferenceArray<T> replace(final Function<AtomicReferenceArray<T>, AtomicReferenceArray<T>> replace) {
        AtomicReferenceArray<T> current, next;
        do {
            current = this.array();
            next = replace.apply(current);
        } while (!arrayRef.compareAndSet(current, next));
        return next;
    }

    @Override
    public T get(final int index) {
        return this.array().get(index);
    }

    @Override
    public T set(int i, T value) {
        return this.array().getAndSet(i, value);
    }

    @Override
    public int length() {
        return this.array().length();
    }

    @Override
    public ImmutableArray<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void prefix(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void suffix(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeFirst(final Object element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(final Object element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        this.replace(current -> new AtomicReferenceArray<>(current.length()));
    }

    @Override
    public ConcurrentArray<T> mutableCopy() {
        return new MutableAtomicArray<>(copy(this.array()));
    }

}
