package net.coljate.feature;

import net.coljate.collection.Collection;

/**
 * Has a specific order.
 *
 * @author ollie
 */
public interface Ordered<T> extends Collection<T> {

    /**
     *
     * @return the first element in this ordered collection, or {@code null} if
     * empty.
     */
    T first();

    /**
     *
     * @return the last element in this ordered collection, of {@code null} if
     * empty.
     */
    T last();

}
