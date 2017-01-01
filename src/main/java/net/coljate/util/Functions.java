package net.coljate.util;

import java.util.function.Function;

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

}
