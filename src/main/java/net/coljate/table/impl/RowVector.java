package net.coljate.table.impl;

import java.util.Iterator;

import net.coljate.list.Array;
import net.coljate.table.Cell;
import net.coljate.table.ImmutableMatrix;
import net.coljate.table.Matrix;
import net.coljate.table.MutableMatrix;

/**
 *
 * @author ollie
 */
public class RowVector<T> implements Matrix<T> {

    private final Array<? extends T> array;

    protected RowVector(final Array<? extends T> array) {
        this.array = array;
    }

    @Override
    public int width() {
        return array.length();
    }

    @Override
    public int height() {
        return 1;
    }

    @Override
    public T get(int x, int y) {
        if (y != 1) {
            throw new IndexOutOfBoundsException();
        }
        return array.get(x);
    }

    @Override
    public MutableMatrix<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableMatrix<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Cell<Integer, Integer, T>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
