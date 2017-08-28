package net.coljate.list.primitive;

import net.coljate.list.Array;

/**
 *
 * @author Ollie
 */
public interface DoubleArray extends DoubleList, Array<Double> {

    double getDouble(int index);

    @Override
    @Deprecated
    default Double get(final int index) {
        return this.getDouble(index);
    }

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return DoubleList.super.contains(object);
    }

    @Override
    DoubleListIterator iterator();

    @Override
    default double[] doubleArrayCopy() {
        final int length = this.length();
        final double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = this.getDouble(i);
        }
        return array;
    }

    @Override
    default MutableDoubleArray mutableCopy() {
        return MutableNativeDoubleArray.copyOf(this);
    }

    @Override
    ImmutableDoubleArray immutableCopy();

}
