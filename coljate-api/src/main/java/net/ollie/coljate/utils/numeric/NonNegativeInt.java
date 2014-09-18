package net.ollie.coljate.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;
import javax.annotation.concurrent.ThreadSafe;

/**
 *
 */
@ThreadSafe
final class NonNegativeInt extends PositiveInteger {

    private static final long serialVersionUID = 1L;

    private final int value;
    private transient BigInteger bigInt;

    NonNegativeInt(final int value) throws NonNegativeException {
        if (value <= 0) {
            throw new NonNegativeException(value);
        }
        this.value = value;
    }

    @Override
    public BigInteger bigIntegerValue() {
        if (bigInt == null) {
            bigInt = BigInteger.valueOf(value);
        }
        return bigInt;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public NonNegativeInteger increment() {
        return NonNegativeInteger.of((long) value + 1);
    }

    public int peekDecrement() {
        return value - 1;
    }

    @Override
    public Optional<NonNegativeInteger> decrement() {
        final int decremented = this.peekDecrement();
        return decremented >= 0
                ? Optional.of(NonNegativeInteger.of(decremented))
                : Optional.empty();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
