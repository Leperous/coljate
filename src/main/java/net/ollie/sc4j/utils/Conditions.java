package net.ollie.sc4j.utils;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public final class Conditions {

    private Conditions() {
    }

    @Nonnull
    public static <T> T checkNotNull(@CheckForNull final T object) {
        if (object == null) {
            throw new IllegalArgumentException();
        } else {
            return object;
        }
    }

    public static void checkIsNonNegative(final int i) {
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
