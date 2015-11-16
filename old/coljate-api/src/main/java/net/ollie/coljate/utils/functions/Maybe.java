package net.ollie.coljate.utils.functions;

import static java.util.Objects.requireNonNull;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Maybe<F, T> extends Function<F, T> {

    @Override
    @Nonnull
    @SuppressWarnings("null")
    default <V> Maybe<V, T> compose(@Nonnull final Function<? super V, ? extends F> before) {
        requireNonNull(before);
        return input -> {
            final F output = before.apply(input);
            return output == null ? null : this.apply(output);
        };
    }

    @Override
    @Nonnull
    @SuppressWarnings("null")
    default <R> Maybe<F, R> andThen(@Nonnull final Function<? super T, ? extends R> after) {
        requireNonNull(after);
        return input -> {
            final T output = this.apply(input);
            return output == null ? null : after.apply(output);
        };
    }

    @Nonnull
    @SuppressWarnings("null")
    default Maybe<F, T> thenIf(final Predicate<? super F> predicate) {
        return input -> predicate.test(input) ? this.apply(input) : null;
    }

    @Nonnull
    static <F, T> Maybe<F, T> of(@Nonnull final Function<? super F, ? extends T> function) {
        requireNonNull(function);
        return input -> input == null ? null : function.apply(input);
    }

}
