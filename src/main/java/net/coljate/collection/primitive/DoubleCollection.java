package net.coljate.collection.primitive;

import java.util.Iterator;

import net.coljate.collection.Collection;

/**
 *
 * @author ollie
 */
public interface DoubleCollection extends Collection<Double> {

    boolean contains(double d);

    @Override
    default boolean contains(final Object object) {
        return object instanceof Double
                && this.contains(((Double) object).doubleValue());
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
