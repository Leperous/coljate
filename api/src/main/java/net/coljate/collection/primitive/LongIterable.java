package net.coljate.collection.primitive;

import java.util.Iterator;
import java.util.function.LongBinaryOperator;

/**
 *
 * @author Ollie
 */
public interface LongIterable extends Iterable<Long>, LongContainer {

    @Override
    LongIterator iterator();

    default long reduce(final LongBinaryOperator operator, final long initialValue) {
        long current = initialValue;
        for (final LongIterator iterator = this.iterator(); iterator.hasNext();) {
            current = operator.applyAsLong(current, iterator.nextLong());
        }
        return current;
    }

    default long longSum() throws ArithmeticException {
        return this.reduce((final long i1, final long i2) -> Math.addExact(i1, i2), 0);
    }

    default long longProduct() throws ArithmeticException {
        return this.reduce((final long i1, final long i2) -> Math.multiplyExact(i1, i2), 1);
    }

    interface LongIterator extends Iterator<Long> {

        long nextLong();

        @Override
        @Deprecated
        default Long next() {
            return this.nextLong();
        }

    }

}
