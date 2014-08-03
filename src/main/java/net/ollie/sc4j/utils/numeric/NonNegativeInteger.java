package net.ollie.sc4j.utils.numeric;

import java.util.Optional;

import java.math.BigInteger;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A non-negative integer, including zero.
 *
 * @author Ollie
 * @see <a href="http://en.wikipedia.org/wiki/Natural_number">Natural number</a>
 */
public abstract class NonNegativeInteger
        extends Number
        implements Comparable<Number> {

    private static final long serialVersionUID = 1L;
    public static final NonNegativeInteger ZERO = new NonNegativeInt(0), ONE = new NonNegativeInt(1);

    @Nonnull
    public static NonNegativeInteger of(final int value) {
        return NonNegativeInt.of(value);
    }

    @Nonnull
    public static NonNegativeInteger of(final BigInteger value) {
        return NonNegativeBigInteger.of(value);
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final int value) {
        return NonNegativeInt.maybe(value);
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final BigInteger value) {
        return NonNegativeBigInteger.maybe(value);
    }

    @Nonnull
    @CheckReturnValue
    public abstract NonNegativeInteger increment();

    public abstract Optional<NonNegativeInteger> decrement();

    public abstract BigInteger bigIntegerValue();

    @Override
    public int intValue() {
        return this.bigIntegerValue().intValueExact();
    }

    @Override
    public long longValue() {
        return this.bigIntegerValue().longValueExact();
    }

    @Override
    public float floatValue() {
        return this.bigIntegerValue().floatValue();
    }

    @Override
    public double doubleValue() {
        return this.bigIntegerValue().doubleValue();
    }

    public boolean isZero() {
        return this.bigIntegerValue().signum() == 0;
    }

    @Override
    public int compareTo(final Number that) {
        if (that instanceof NonNegativeInteger) {
            return this.compareTo((NonNegativeInteger) that);
        } else if (that instanceof BigInteger) {
            return this.compareTo((BigInteger) that);
        } else {
            final double d1 = this.doubleValue();
            final double d2 = that.doubleValue();
            return Double.compare(d1, d2);
        }
    }

    public int compareTo(final NonNegativeInteger that) {
        return this.compareTo(that.bigIntegerValue());
    }

    public int compareTo(final BigInteger that) {
        return this.bigIntegerValue().compareTo(that);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Number
                && this.equals((Number) object);
    }

    public boolean equals(@Nonnull final Number that) {
        return this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return this.intValue();
    }

}
