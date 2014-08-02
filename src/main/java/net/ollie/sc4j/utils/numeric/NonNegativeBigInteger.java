package net.ollie.sc4j.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;

/**
 *
 * @author Ollie
 */
public class NonNegativeBigInteger extends NonNegativeInteger {

    private static final long serialVersionUID = 1L;

    public static NonNegativeBigInteger of(final BigInteger value) {
        return new NonNegativeBigInteger(value);
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
    public int intValue() {
        return value.intValueExact();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public long longValue() {
        return value.longValueExact();
    }

}
