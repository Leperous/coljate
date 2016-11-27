package net.coljate.util;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author ollie
 */
public class Equality {

    public static boolean orderedEquals(final Iterable<?> it1, final Iterable<?> it2) {
        final Iterator<?> i1 = it1.iterator();
        final Iterator<?> i2 = it2.iterator();
        while (i1.hasNext()) {
            final Object o1 = i1.next();
            if (!i2.hasNext() || !Objects.equals(o1, i2.next())) {
                return false;
            }
        }
        return !i2.hasNext();
    }

}
