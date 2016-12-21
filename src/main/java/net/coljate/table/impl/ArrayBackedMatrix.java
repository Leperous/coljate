package net.coljate.table.impl;

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

}
