package net.ollie.sc4j.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;

/**
 *
 * @author Ollie
 */
public class NonNegativeBigInteger extends NonNegativeInteger {

    private static final long serialVersionUID = 1L;
    private static final NonNegativeBigInteger BI_ZERO = new NonNegativeBigInteger(BigInteger.ZERO);

    public static NonNegativeBigInteger of(final BigInteger value) {
        return value.signum() == 0
                ? BI_ZERO
                : new NonNegativeBigInteger(value);
    }

    private final BigInteger value;

    protected NonNegativeBigInteger(final BigInteger value) {
        if (value.signum() < 0) {
            throw new IllegalArgumentException("Value is negative: " + value);
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
    public boolean isZero() {
        return value.signum() == 0;
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
