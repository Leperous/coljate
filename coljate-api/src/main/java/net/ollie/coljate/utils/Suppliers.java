package net.ollie.coljate.utils;

import java.util.NoSuchElementException;

/**
 *
 * @author Ollie
 */
public final class Suppliers {

    private Suppliers() {
    }

    public static <V> V noSuchElement() {
        throw new NoSuchElementException();
    }

}
