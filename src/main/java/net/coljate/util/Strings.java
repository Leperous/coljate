package net.coljate.util;

import java.util.StringJoiner;

/**
 *
 * @author ollie
 */
public class Strings {

    public static String toString(final Iterable<?> iterable) {
        final StringJoiner joiner = new StringJoiner("[", ",", "]");
        iterable.forEach(element -> joiner.add(element == iterable ? "(self)" : String.valueOf(element)));
        return joiner.toString();
    }

}
