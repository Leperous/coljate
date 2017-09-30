package net.coljate.collection.primitive;

import net.coljate.collection.Collection;

/**
 *
 * @author Ollie
 */
public interface LongCollection extends Collection<Long>, LongIterable {

    boolean contains(long i);

    @Override
    default boolean contains(final Object object) {
        return (object instanceof Long || object instanceof Integer)
                && this.contains(((Number) object).longValue());
    }

}
