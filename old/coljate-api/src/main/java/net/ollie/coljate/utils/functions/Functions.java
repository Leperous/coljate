package net.ollie.coljate.utils.functions;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;

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

    @SafeVarargs
    @CheckForNull
    public static <T> T firstNonNull(final Supplier<? extends T>... suppliers) {
        return firstThat(Objects::nonNull, suppliers);
    }

    @SafeVarargs
    public static <T> T firstThat(final Predicate<? super T> predicate, final Supplier<? extends T>... suppliers) {
        for (final Supplier<? extends T> supplier : suppliers) {
            final T supplied = supplier.get();
            if (predicate.test(supplied)) {
                return supplied;
            }
        }
        return null;
    }

}
