package net.coljate.util;

import net.coljate.util.iterator.Iterators;
import net.coljate.collection.Collection;

/**
 *
 * @author Ollie
 */
public class Iterables {

    public static int count(final Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).count();
        }
        if (iterable instanceof java.util.Collection) {
            return ((java.util.Collection) iterable).size();
        }
        return Iterators.count(iterable.iterator());
    }

    public static <T> T last(final Iterable<T> iterable) {
        return Iterators.last(iterable.iterator());
    }

}
