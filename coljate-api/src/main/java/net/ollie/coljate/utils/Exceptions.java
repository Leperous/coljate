package net.ollie.coljate.utils;

import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
public final class Exceptions {

    private Exceptions() {
    }

    public static <V> V illegalArgument(final String reason) {
        throw new IllegalArgumentException(reason);
    }

    public static <V> V noSuchElement() {
        throw new NoSuchElementException();
    }

}
