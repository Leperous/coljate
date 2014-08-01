package net.ollie.sc4j.utils;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Ollie
 */
public final class Functions {

    private Functions() {
    }

    public static <T> Function<T, T> satisfying(final Predicate<? super T> predicate) {
        return t -> predicate.test(t) ? t : null;
    }

}
