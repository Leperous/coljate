package net.coljate.util;

import java.util.function.Function;

/**
 *
 * @author ollie
 */
public class Functions {

    public static <L, R> R ifNonNull(final L value, final Function<? super L, ? extends R> transform) {
        return value == null ? null : transform.apply(value);
    }

}
