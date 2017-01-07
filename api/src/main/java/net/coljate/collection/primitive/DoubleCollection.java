package net.coljate.collection.primitive;

import java.util.Iterator;

import net.coljate.collection.Collection;

/**
 *
 * @author ollie
 */
public interface DoubleCollection extends Collection<Double> {

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
        return object instanceof Double
                && this.contains(((Number) object).doubleValue());
    }

    @Override
    DoubleIterator iterator();

    interface DoubleIterator extends Iterator<Double> {

        double nextDouble();

        @Override
        @Deprecated
        default Double next() {
            return this.nextDouble();
        }

    }

}
