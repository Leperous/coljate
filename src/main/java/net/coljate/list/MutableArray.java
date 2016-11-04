package net.coljate.list;

import net.coljate.Collection;
import net.coljate.utils.Arrays;

/**
 *
 * @author ollie
 */
public class MutableArray<T> implements Array<T>, MutableList<T> {

    public static <T> MutableArray<T> copyOf(final Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    private Object[] array;
    private int count;

    protected MutableArray(final Object[] array, final int count) {
        this.array = array;
        this.count = count;
    }

    @Override
    public T get(final int index) {
        return (T) array[index];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public boolean contains(final Object object) {
        return Arrays.contains(array, object);
    }

    @Override
    public ArrayIterator<T> iterator() {
        return new ArrayIterator<T>(this) {

            @Override
            public void remove() {
                MutableArray.this.remove(this.currentIndex());
            }

        };
    }

    public void remove(final int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeFirst(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
