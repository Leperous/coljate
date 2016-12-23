package net.coljate.collection.primitive;

import java.util.Iterator;

import net.coljate.collection.Collection;

/**
 *
 * @author ollie
 */
public interface LongCollection extends Collection<Long> {

    boolean contains(long i);

    @Override
    default boolean contains(final Object object) {
        return (object instanceof Long || object instanceof Integer)
                && this.contains(((Number) object).longValue());
    }

    @Override
    LongIterator iterator();

    interface LongIterator extends Iterator<Long> {

        long nextLong();

        @Override
        @Deprecated
        default Long next() {
            return this.nextLong();
        }

    }

}
