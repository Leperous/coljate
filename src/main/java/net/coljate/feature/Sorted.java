package net.coljate.feature;

import java.util.Comparator;

/**
 * Has a specific order, based on some {@link #comparator}.
 *
 * @author ollie
 */
public interface Sorted<T> extends Ordered<T> {

    /**
     *
     * @return the comparator used to sort this collection.
     */
    Comparator<? super T> comparator();

    T last();

}
