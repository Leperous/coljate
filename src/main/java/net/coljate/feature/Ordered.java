package net.coljate.feature;

import net.coljate.Collection;

/**
 * Has a specific order.
 *
 * @author ollie
 */
public interface Ordered<T> extends Collection<T> {

    T first();

    T last();

}
