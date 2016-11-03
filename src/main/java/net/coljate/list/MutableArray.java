package net.coljate.list;

import java.util.Iterator;

import net.coljate.Collection;
import net.coljate.utils.Arrays;

/**
 *
 * @author ollie
 */
public class MutableArray<T> extends Array<T> implements MutableList<T> {

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
    protected Object[] underlying() {
        return array;
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
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public ImmutableList<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
