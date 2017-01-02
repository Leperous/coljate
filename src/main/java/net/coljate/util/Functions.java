package net.coljate.util;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author ollie
 */
public class Functions {

    public static <L, R> R ifNonNull(@Nullable final L value, @Nonnull final Function<? super L, ? extends R> transform) {
        return value == null ? null : transform.apply(value);
    }

    public static <F, T> T ifBothNonNull(
            final F left,
            final F right,
            final BiFunction<? super F, ? super F, ? extends T> transformation,
            final Supplier<? extends T> ifNull) {
        return left == null && right == null
                ? ifNull.get()
                : transformation.apply(left, right);
    }

}
