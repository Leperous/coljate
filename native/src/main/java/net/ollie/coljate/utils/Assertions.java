package net.ollie.coljate.utils;

import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public class Assertions {

    public static int checkNonNegative(final int input) {
        return checkNonNegative(input, () -> "Input cannot be negative, but was [" + input + "}!");
    }

    public static int checkNonNegative(final int input, final Supplier<String> ifNegative) {
        if (input < 0) {
            throw new IllegalArgumentException(ifNegative.get());
        }
        return input;
    }

}
