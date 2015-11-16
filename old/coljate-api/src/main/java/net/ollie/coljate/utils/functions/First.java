package net.ollie.coljate.utils.functions;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
@FunctionalInterface
public interface First<T> extends Function<Predicate<? super T>, T> {

    @CheckForNull
    @Override
    T apply(Predicate<? super T> predicate);

    @CheckForNull
    default T nonNull() {
        return this.apply(Objects::nonNull);
    }

    static <T> First<T> of(final Supplier<? extends T> supplier) {
        final First<T> first = (predicate) -> null;
        return first.or(supplier);
    }

    default First<T> or(final Supplier<? extends T> supplier) {
        return predicate -> {
            final T got = this.apply(predicate);
            if (predicate.test(got)) {
                return got;
            } else {
                final T supplied = supplier.get();
                return predicate.test(supplied) ? supplied : null;
            }
        };
    }

}
