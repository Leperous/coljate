package net.ollie.sc4j.utils;

import java.util.Iterator;

/**
 *
 * @author Ollie
 */
public final class Strings {

    private Strings() {
    }

    public static String toString(final Iterable<?> iterable, final String separator) {
        return toString(iterable, iterable, separator);
    }

    public static String toString(final Object self, final Iterable<?> iterable, final String separator) {
        final Iterator<?> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return "[]";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            if (self == next) {
                sb.append("(this)");
            } else {
                sb.append(next);
            }
            if (iterator.hasNext()) {
                sb.append(separator);
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
