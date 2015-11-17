package net.ollie.coljate.lists;

import java.util.Arrays;
import java.util.Iterator;

import static net.ollie.coljate.utils.Assertions.checkNonNegative;

/**
 *
 * @author Ollie
 * @see java.util.ArrayList
 */
public class MutableArrayList<T> implements MutableArray<T> {

    private static final Object[] EMPTY = new Object[0];

    public static <T> MutableArray<T> of() {
        return new MutableArrayList<>(0);
    }

    private Object[] array;
    private int size;

    public MutableArrayList(final int capacity) {
        checkNonNegative(capacity);
        this.array = capacity == 0 ? EMPTY : new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    private T[] typedArray() {
        return (T[]) array;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public void setCapacity(final int capacity) {
        checkNonNegative(size);
        if (capacity == this.capacity()) {
            return;
        }
        array = capacity == 0 ? EMPTY : Arrays.copyOf(array, capacity);
    }

    @Override
    public int count() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        return (T) array[index];
    }

    @Override
    public boolean add(final T element) {
        this.ensureCapacity(size + 1);
        array[size++] = element;
        return true;
    }

    @Override
    public T set(final int index, final T element) {
        final T previous = this.get(index);
        array[index] = element;
        return previous;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean removeOnce(final Object element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean removeAll(final Object element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean clear() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableList<T> immutableCopy() {
        return ImmutableArrayList.of(this.typedArray());
    }

}
