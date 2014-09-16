package net.ollie.coljate.utils;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public final class Conditions {

    private Conditions() {
    }

    @Nonnull
    public static <T> T checkNotNull(final T object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
        return object;
    }

    public static int checkIsNonNegative(final int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Value [" + i + "] was not positive!");
        }
        return i;
    }

    public static boolean checkIsTrue(final boolean predicate) {
        if (!predicate) {
            throw new IllegalArgumentException();
        }
        return predicate;
    }

    public static <T> T[] checkIsEmpty(final T[] array) {
        checkIsTrue(array.length == 0);
        return array;
    }

}
