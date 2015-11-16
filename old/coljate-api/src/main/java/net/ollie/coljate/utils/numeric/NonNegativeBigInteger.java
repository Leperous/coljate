package net.ollie.coljate.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
final class NonNegativeBigInteger extends PositiveInteger {

    private static final long serialVersionUID = 1L;

    @Nonnull
    public static NonNegativeInteger of(final long value) throws NonNegativeException {
        return of(BigInteger.valueOf(value));
    }

    @Nonnull
    public static NonNegativeInteger of(final BigInteger value) throws NonNegativeException {
        return value.signum() == 0
                ? NonNegativeInteger.ZERO
                : new NonNegativeBigInteger(value);
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final BigInteger value) {
        final int s = value.signum();
        if (s == 0) {
            return ZERO;
        } else if (s < 0) {
            return null;
        } else {
            return new NonNegativeBigInteger(value);
        }
    }

    private final BigInteger value;

    NonNegativeBigInteger(final BigInteger value) throws NonNegativeException {
        if (value.signum() <= 0) {
            throw new NonNegativeException(value);
        }
        this.value = value;
    }

    @Override
    public NonNegativeInteger increment() {
        return of(value.add(BigInteger.ONE));
    }

    @Override
    public Optional<NonNegativeInteger> decrement() {
        final BigInteger decremented = value.subtract(BigInteger.ONE);
        return decremented.signum() < 0
                ? Optional.empty()
                : Optional.of(of(decremented));
    }

    @Override
    public BigInteger bigIntegerValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
