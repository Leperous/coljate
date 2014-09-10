package net.ollie.coljate.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;

/**
 *
 * @author Ollie
 */
@SuppressWarnings("EqualsAndHashcode")
final class Infinity extends NonNegativeInteger {

    private static final long serialVersionUID = 1L;

    protected Infinity() {
    }

    @Override
    public NonNegativeInteger increment() {
        return this;
    }

    @Override
    public Optional<NonNegativeInteger> decrement() {
        return Optional.of(this);
    }

    @Override
    public BigInteger bigIntegerValue() {
        throw new ArithmeticException("Infinite!");
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public double doubleValue() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public float floatValue() {
        return Float.POSITIVE_INFINITY;
    }

    @Override
    public NonNegativeInteger plus(final NonNegativeInteger that) {
        return this;
    }

    @Override
    public NonNegativeInteger times(final NonNegativeInteger that) {
        if (that.isZero()) {
            throw new ArithmeticException("Infinity times zero is undefined!");
        } else {
            return this;
        }
    }

    @Override
    public Sorting compareTo(final NonNegativeInteger that) {
        return that instanceof Infinity ? Sorting.SAME : Sorting.AFTER;
    }

    @Override
    public boolean equals(final Number that) {
        return that instanceof Infinity;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Infinity";
    }

}
