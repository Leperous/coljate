package net.ollie.sc4j.utils;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public final class Suppliers {

    private Suppliers() {
    }

    public static <V> Supplier<V> noSuchElement() {
        return () -> {
            throw new NoSuchElementException();
        };
    }

}
