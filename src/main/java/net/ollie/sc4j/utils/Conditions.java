package net.ollie.sc4j.utils;

/**
 *
 * @author Ollie
 */
public final class Conditions {

    private Conditions() {
    }

    public static void checkIsPositive(final int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Value [" + i + "] was not positive!");
        }
    }

    public static void checkIsTrue(final boolean predicate) {
        if (!predicate) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T[] checkIsEmpty(final T[] array) {
        checkIsTrue(array.length == 0);
        return array;
    }

}
