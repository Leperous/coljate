package net.coljate.feature;

import java.util.Comparator;

/**
 * Has a specific order, based on some {@link #comparator}.
 *
 * @author ollie
 */
public interface Sorted<T> extends Ordered<T> {

    Comparator<? super T> comparator();

    T last();

}
