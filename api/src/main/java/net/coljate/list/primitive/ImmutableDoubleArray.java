package net.coljate.list.primitive;

import net.coljate.list.ImmutableArray;

/**
 *
 * @author Ollie
 */
public interface ImmutableDoubleArray
        extends DoubleArray, ImmutableDoubleList, ImmutableArray<Double> {

    @Override
    default ImmutableDoubleListIterator iterator() {
        return new ImmutableDoubleArrayIterator(this);
    }

    @Override
    @Deprecated
    default ImmutableDoubleArray immutableCopy() {
        return this;
    }

    class ImmutableDoubleArrayIterator extends DoubleArrayIterator implements ImmutableDoubleListIterator {

        public ImmutableDoubleArrayIterator(DoubleArray array) {
            super(array);
        }

    }

}
