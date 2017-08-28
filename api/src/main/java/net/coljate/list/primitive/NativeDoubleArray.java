package net.coljate.list.primitive;

/**
 *
 * @author Ollie
 */
public class NativeDoubleArray implements DoubleArray {

    private final double[] array;

    public NativeDoubleArray(final double[] array) {
        this.array = array;
    }

    @Override
    public double getDouble(final int index) {
        return array[index];
    }

    @Override
    public DoubleListIterator iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableDoubleArray immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public int length() {
        return array.length;
    }

}
