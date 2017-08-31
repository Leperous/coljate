package net.coljate.table.primitive;

import net.coljate.table.ImmutableMatrix;
import net.coljate.table.MutableMatrix;

/**
 *
 * @author Ollie
 */
public class DoubleArrayMatrix implements DoubleMatrix {

    private final double[][] array;

    protected DoubleArrayMatrix(final double[][] array) {
        this.array = array;
    }

    @Override
    public double getValue(final int x, final int y) {
        return array[x][y];
    }

    @Override
    public int rows() {
        return array.length;
    }

    @Override
    public int columns() {
        return array[0].length;
    }

    @Override
    public MutableMatrix<Double> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableMatrix<Double> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

}
