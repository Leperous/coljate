package net.coljate.list.primitive;

import net.coljate.list.ImmutableArray;

/**
 *
 * @author Ollie
 */
public interface ImmutableDoubleArray
        extends DoubleArray, ImmutableDoubleList, ImmutableArray<Double> {

    @Override
    ImmutableDoubleListIterator iterator();

    @Override
    @Deprecated
    default ImmutableDoubleArray immutableCopy() {
        return this;
    }

}
