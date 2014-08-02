package net.ollie.sc4j.utils.numeric;

import java.util.Optional;

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

    public static NonNegativeInteger maybe(final int value) {
        return NonNegativeInt.maybe(value);
    }

    @Nonnull
    @CheckReturnValue
    public abstract NonNegativeInteger increment();

    public abstract Optional<NonNegativeInteger> decrement();

    public abstract boolean isZero();

    @Override
    public long longValue() {
        return this.intValue();
    }

    @Override
    public float floatValue() {
        return this.intValue();
    }

    @Override
    public double doubleValue() {
        return this.intValue();
    }

    @Override
    public int compareTo(final Number that) {
        final double d1 = this.doubleValue();
        final double d2 = that.doubleValue();
        return Double.compare(d1, d2);
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
