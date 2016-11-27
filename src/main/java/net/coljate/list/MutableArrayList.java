package net.coljate.list;

import net.coljate.utils.Arrays;

/**
 *
 * @author ollie
 * @see java.util.ArrayList
 */
public class MutableArrayList<T> implements MutableArray<T> {

    @SafeVarargs
    public static <T> MutableArrayList<T> copyOf(final T... elements) {
        return new MutableArrayList<>(elements, elements.length);
    }

    private Object[] array;
    private int count;

    protected MutableArrayList(final Object[] array, final int count) {
        this.array = array;
        this.count = count;
    }

    @Override
    public T get(final int index) {
        return (T) array[index];
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public ListIterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void prefix(final T element) {
        final Object[] newArray = new Object[count + 1];
        newArray[0] = element;
        System.arraycopy(array, 0, newArray, 1, count);
        array = newArray;
        count++;
    }

    @Override
    public void suffix(final T element) {
        this.ensureCapacity(count + 1);
        array[count++] = element;
    }

    private void ensureCapacity(final int size) {
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
    }

    @Override
    public boolean removeFirst(T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableArray<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Array
                && this.equals((Array) obj);
    }

    private final class ArrayListIterator implements ListIterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public T next() {
            return get(index++);
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
