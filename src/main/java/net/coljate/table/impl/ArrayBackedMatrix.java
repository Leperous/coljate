package net.coljate.table.impl;

import java.util.Iterator;

import net.coljate.table.Cell;
import net.coljate.table.ImmutableCell;
import net.coljate.table.ImmutableMatrix;
import net.coljate.table.Matrix;
import net.coljate.table.MutableMatrix;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
public class ArrayBackedMatrix<T> implements Matrix<T> {

    private final Object[][] matrix;

    protected ArrayBackedMatrix(final Object[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int width() {
        return matrix.length;
    }

    @Override
    public int height() {
        return matrix.length == 0 ? 0 : matrix[0].length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int x, int y) {
        return (T) matrix[x][y];
    }

    @Override
    public Object[][] arrayCopy() {
        return Arrays.copy(matrix);
    }

    @Override
    public MutableMatrix<T> mutableCopy() {
        return new MutableArrayBackedMatrix<>(this.arrayCopy());
    }

    @Override
    public ImmutableMatrix<T> immutableCopy() {
        return new ImmutableArrayBackedMatrix<>(this.arrayCopy());
    }

    @Override
    public Iterator<Cell<Integer, Integer, T>> iterator() {
        return new MatrixIterator();
    }

    private final class MatrixIterator implements Iterator<Cell<Integer, Integer, T>> {

        private int x, y;

        @Override
        public boolean hasNext() {
            return x < matrix.length && y < matrix[x].length;
        }

        @Override
        public Cell<Integer, Integer, T> next() {
            final int y = this.y++, x = this.x;
            final T next = get(x, y);
            if (this.y == matrix[x].length) {
                this.x += 1;
                this.y = 0;
            }
            return new ImmutableCell<>(x, y, next);
        }

    }

}
