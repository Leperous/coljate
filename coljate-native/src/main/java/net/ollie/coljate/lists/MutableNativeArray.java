package net.ollie.coljate.lists;

import net.ollie.coljate.list.ImmutableArray;
import net.ollie.coljate.list.AbstractList;
import net.ollie.coljate.list.MutableArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author ollie
 */
public class MutableNativeArray<T>
        extends AbstractList<T>
        implements MutableArray<T> {

    @SafeVarargs
    public static <T> MutableArray<T> copyOf(final T... elements) {
        return new MutableNativeArray<>(0, Arrays.copyOf(elements, elements.length));
    }

    private final int offset;
    private Object[] array;

    MutableNativeArray(final int offset, final Object[] array) {
        this.offset = offset;
        this.array = array;
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = offset;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                return MutableNativeArray.this.get(index++);
            }

            @Override
            public void remove() {
                array[index - offset - 1] = null;
            }

        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        return (T) array[offset + index];
    }

    @Override
    public MutableArray<T> tail() {
        return new MutableNativeArray<>(offset + 1, array);
    }

    @Override
    public ImmutableArray<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MutableArray<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public void setCapacity(int capacity) {
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public T set(int index, T element) {
        final T previous = this.get(index);
        array[index] = element;
        return previous;
    }

    @Override
    public void suffix(final T element) {
        final int originalLength = array.length;
        this.setCapacity(array.length + 1);
        array[originalLength] = element;
    }

    @Override
    public void prefix(final T element) {
        final Object[] newArray = new Object[array.length + 1];
        System.arraycopy(array, 0, newArray, 1, array.length);
        array = newArray;
        array[0] = element;
    }

    @Override
    public boolean removeOnce(final Object element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                array[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        array = new Object[array.length];
    }

}
