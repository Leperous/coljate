package net.coljate.collection.primitive;

import net.coljate.Container;

/**
 *
 * @author Ollie
 */
public interface IntegerContainer extends Container {

    boolean contains(int i);

    @Override
    default boolean contains(Object object) {
        return object instanceof Integer
                && this.contains(((Integer) object).intValue());
    }

}
