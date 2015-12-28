package net.ollie.coljate.lists;

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

    private Object[] array;

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public MutableArray<T> tail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
