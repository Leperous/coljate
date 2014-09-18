package net.ollie.coljate.utils.functions;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    public static <T> Consumer<T> doNothing() {
        return object -> {
        };
    }

    public static <T> Supplier<T> nothing() {
        return () -> null;
    }

}
