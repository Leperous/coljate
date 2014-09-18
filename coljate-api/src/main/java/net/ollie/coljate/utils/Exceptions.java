package net.ollie.coljate.utils;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

import static net.ollie.coljate.utils.functions.Functions.nothing;

/**
 *
 * @author Ollie
 */
public final class Exceptions {

    private Exceptions() {
    }

    public static <V> V illegalArgument() {
        return illegalArgument(nothing());
    }

    public static <V> V illegalArgument(final String reason) {
        throw new IllegalArgumentException(reason);
    }

    public static <V> V illegalArgument(final Supplier<String> reason) {
        throw new IllegalArgumentException(reason.get());
    }

    public static <V> V noSuchElement() {
        throw new NoSuchElementException();
    }

}
