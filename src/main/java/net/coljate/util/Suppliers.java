package net.coljate.util;

import java.util.function.Supplier;

/**
 *
 * @author ollie
 */
public class Suppliers {

    public static <T> T firstNonNull(final T first, final T second) {
        return first == null ? second : first;
    }

    public static <T> T firstNonNull(final T first, final Supplier<? extends T> second) {
        return first == null ? second.get() : first;
    }

}
