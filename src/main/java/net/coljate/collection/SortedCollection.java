package net.coljate.collection;

import java.util.Comparator;

import net.coljate.feature.Ordered;

/**
 * Has a specific order, based on some {@link #comparator}.
 *
 * @author ollie
 */
public interface SortedCollection<T> extends Ordered<T> {

    /**
     *
     * @return the comparator used to sort this collection.
     */
    Comparator<? super T> comparator();

    T last();

}
