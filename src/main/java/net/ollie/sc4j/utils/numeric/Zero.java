package net.ollie.sc4j.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;

/**
 *
 * @author Ollie
 */
final class Zero extends NonNegativeInteger {

    private static final long serialVersionUID = 1L;

    protected Zero() {
    }

    @Override
    public NonNegativeInteger increment() {
        return ONE;
    }

    @Override
    public Optional<NonNegativeInteger> decrement() {
        return Optional.empty();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.ZERO;
    }

    @Override
    public Sorting compareTo(final NonNegativeInteger that) {
        return that instanceof Infinity
                ? Sorting.BEFORE
                : super.compareTo(that);
    }

    @Override
    public NonNegativeInteger plus(final NonNegativeInteger that) {
        return that;
    }

    @Override
    public boolean isZero() {
        return true;
    }

}
