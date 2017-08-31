package net.coljate.table.primitive;

import net.coljate.table.Matrix;

/**
 *
 * @author Ollie
 */
public interface DoubleMatrix extends Matrix<Double> {

    double getValue(int x, int y);

    @Override
    @Deprecated
    default Double get(final int x, final int y) {
        return this.getValue(x, y);
    }

    default DoubleMatrix product(final DoubleMatrix that) {
        throw new UnsupportedOperationException(); //TODO
    }

}
