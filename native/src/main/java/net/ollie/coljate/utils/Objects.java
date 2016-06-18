package net.ollie.coljate.utils;

import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public class Objects {

    public static <T> T firstNonNull(final T first, final Supplier<? extends T> supplier) {
        return first != null ? first : supplier.get();
    }

}
