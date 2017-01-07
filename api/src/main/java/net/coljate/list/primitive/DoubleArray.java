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
    MutableDoubleArray mutableCopy();

}
