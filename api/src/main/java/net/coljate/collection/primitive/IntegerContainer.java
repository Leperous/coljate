package net.coljate.collection.primitive;

import net.coljate.Container;

/**
 *
 * @author Ollie
 */
public interface IntegerContainer extends Container {

    boolean contains(int i);

    @Override
    default boolean contains(final Object object) {
        return object instanceof Integer
                && this.contains(((Integer) object).intValue());
    }

    default boolean contains(final long l) {
        return (l >= Integer.MIN_VALUE && l <= Integer.MAX_VALUE)
                && this.contains((int) l);
    }

}
