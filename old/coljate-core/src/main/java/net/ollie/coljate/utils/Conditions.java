package net.ollie.coljate.utils;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.ollie.coljate.utils.Exceptions.illegalArgument;

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
        return object == null ? illegalArgument() : object;
    }

    public static int checkIsNonNegative(final int i) {
        return i < 0 ? illegalArgument(() -> "Negative value " + i) : i;
    }

    public static boolean checkIsTrue(final boolean predicate) {
        return predicate ? predicate : illegalArgument();
    }

    public static <T> T checkIsTrue(final T object, final Predicate<? super T> predicate, final Supplier<String> reason) {
        return predicate.test(object) ? object : illegalArgument(reason);
    }

    public static <T> T checkIsTrue(final T object, final Predicate<? super T> predicate, final String reason) {
        return predicate.test(object) ? object : illegalArgument(reason);
    }

    public static <T> T checkIsFalse(final T object, final Predicate<? super T> predicate, final Supplier<String> reason) {
        return checkIsTrue(object, predicate.negate(), reason);
    }

    public static <T> T checkIsFalse(final T object, final Predicate<? super T> predicate, final String reason) {
        return checkIsTrue(object, predicate.negate(), reason);
    }

    public static <T> T[] checkIsEmpty(final T[] array) {
        checkIsTrue(array.length == 0);
        return array;
    }

}
