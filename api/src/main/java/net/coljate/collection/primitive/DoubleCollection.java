package net.coljate.collection.primitive;

import java.util.Iterator;

import net.coljate.collection.Collection;
import net.coljate.list.primitive.ImmutableNativeDoubleArray;
import net.coljate.list.primitive.MutableNativeDoubleArray;

/**
 *
 * @author Ollie
 */
public interface DoubleCollection extends DoubleContainer, Collection<Double> {

    @Override
    default boolean contains(final double d) {
        for (final DoubleIterator iterator = this.iterator(); iterator.hasNext();) {
            if (iterator.nextDouble() == d) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return DoubleContainer.super.contains(object);
    }

    /**
     * @return the doubles in this collection copied into a new array.
     */
    default double[] doubleArrayCopy() {
        final double[] array = new double[this.count()];
        int index = 0;
        for (final DoubleIterator iterator = this.iterator(); iterator.hasNext();) {
            array[index++] = iterator.nextDouble();
        }
        return array;
    }

    @Override
    DoubleIterator iterator();

    @Override
    default MutableDoubleCollection mutableCopy() {
        return MutableNativeDoubleArray.copyOf(this);
    }

    @Override
    default ImmutableDoubleCollection immutableCopy() {
        return ImmutableNativeDoubleArray.copyOf(this);
    }

    static DoubleCollection copyOf(final double[] array) {
        return MutableNativeDoubleArray.copyOf(array);
    }

    interface DoubleIterator extends Iterator<Double> {

        double nextDouble();

        @Override
        @Deprecated
        default Double next() {
            return this.nextDouble();
        }

    }

}
