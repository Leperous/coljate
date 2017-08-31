package net.coljate.util;

import java.util.function.Supplier;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class Suppliers {

    public static <T> T firstNonNull(@CheckForNull final T first, final T second) {
        return first == null ? second : first;
    }

    public static <T> T firstNonNull(@CheckForNull final T first, @Nonnull final Supplier<? extends T> second) {
        return first == null ? second.get() : first;
    }

}
