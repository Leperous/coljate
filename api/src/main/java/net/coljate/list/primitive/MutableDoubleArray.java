package net.coljate.list.primitive;

import net.coljate.list.MutableArray;

/**
 *
 * @author Ollie
 */
public interface MutableDoubleArray
        extends DoubleArray, MutableDoubleList, MutableArray<Double> {

    @Override
    MutableDoubleArray mutableCopy();

}
