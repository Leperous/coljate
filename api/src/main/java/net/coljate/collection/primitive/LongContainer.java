package net.coljate.collection.primitive;

import net.coljate.Container;

/**
 *
 * @author Ollie
 */
public interface LongContainer extends Container {

    boolean contains(long i);

    @Override
    default boolean contains(final Object object) {
        return (object instanceof Long || object instanceof Integer)
                && this.contains(((Number) object).longValue());
    }

}
