package net.ollie.coljate.utils.numeric;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public abstract class PositiveInteger extends NonNegativeInteger {

    private static final long serialVersionUID = 1L;

    @Nonnull
    public static PositiveInteger of(final int value) throws NonNegativeException {
        final NonNegativeInteger i = NonNegativeInteger.of(value);
        return i.maybePositive().orElseThrow(() -> new IllegalArgumentException("Value [" + value + "] was not a positive integer!"));
    }

    @Nonnull
    public static PositiveInteger of(final Number value) throws NonNegativeException {
        final NonNegativeInteger i = NonNegativeInteger.of(value);
        return i.maybePositive().orElseThrow(() -> new IllegalArgumentException("Value [" + value + "] was not a positive integer!"));
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public Optional<PositiveInteger> maybePositive() {
        return Optional.of(this);
    }

}
