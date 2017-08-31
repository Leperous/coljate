package net.coljate.table.primitive;

/**
 *
 * @author Ollie
 */
public class MutableDoubleArrayMatrix extends DoubleArrayMatrix implements MutableDoubleMatrix {

    private final double[][] array;

    public MutableDoubleArrayMatrix(final double[][] array) {
        super(array);
        this.array = array;
    }

    @Override
    public void set(int x, int y, double value) {
        array[x][y] = value;
    }

}
