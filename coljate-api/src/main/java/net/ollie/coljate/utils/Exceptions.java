package net.ollie.coljate.utils;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public final class Exceptions {

    private Exceptions() {
    }

    public static <V> V illegalArgument() {
        return illegalArgument(null);
    }

    public static <V> V illegalArgument(final Supplier<String> reason) {
        throw new IllegalArgumentException(reason.get());
    }

    public static <V> V noSuchElement() {
        throw new NoSuchElementException();
    }

}
