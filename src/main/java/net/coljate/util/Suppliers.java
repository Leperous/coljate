package net.coljate.util;

/**
 *
 * @author ollie
 */
public class Suppliers {

    public static <T> T firstNonNull(final T first, final T second) {
        return first == null ? second : first;
    }

}
