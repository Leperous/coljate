package net.ollie.coljate.utils.numeric;

import java.util.Optional;
import java.util.OptionalInt;

import net.ollie.coljate.imposed.sorting.Sortable;

import java.math.BigInteger;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A non-negative integer, including zero.
 *
 * Likely either a wrapper around an int (so similar in cost to an {@link Integer}) or a {@link BigInteger}.
 *
 * @author Ollie
 * @see <a href="http://en.wikipedia.org/wiki/Natural_number">Natural number</a>
 * @see Integer
 */
public abstract class NonNegativeInteger
        extends Number
        implements Sortable<Number> {

    private static final long serialVersionUID = 1L;
    public static final NonNegativeInteger ZERO = new Zero(), ONE = new NonNegativeInt(1), INFINITY = new Infinity();

    @Nonnull
    public static NonNegativeInteger of(final int value) throws NonNegativeException {
        return NonNegativeInt.of(value);
    }

    @Nonnull
    public static NonNegativeInteger of(final long value) throws NonNegativeException {
        return value >= Integer.MAX_VALUE
                ? NonNegativeBigInteger.of(value)
                : of((int) value);
    }

    @Nonnull
    public static NonNegativeInteger of(final BigInteger value) throws NonNegativeException {
        return NonNegativeBigInteger.of(value);
    }

    @Nonnull
    public static NonNegativeInteger of(final Number number) throws NonNegativeException {
        if (number instanceof NonNegativeInteger) {
            return (NonNegativeInteger) number;
        } else if (number instanceof BigInteger) {
            return of((BigInteger) number);
        } else {
            return of(number.intValue());
        }
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final int value) {
        return NonNegativeInt.maybe(value);
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final OptionalInt value) {
        return NonNegativeInt.maybe(value);
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final BigInteger value) {
        return NonNegativeBigInteger.maybe(value);
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final Number number) {
        return number == null ? null : NonNegativeInt.maybe(number.intValue());
    }

    @Nonnull
    @CheckReturnValue
    public abstract NonNegativeInteger increment();

    public abstract Optional<NonNegativeInteger> decrement();

    public abstract BigInteger bigIntegerValue();

    public NonNegativeInteger plus(final NonNegativeInteger that) {
        return NonNegativeBigInteger.of(this.bigIntegerValue().add(that.bigIntegerValue()));
    }

    public NonNegativeInteger times(final NonNegativeInteger that) {
        return NonNegativeBigInteger.of(this.bigIntegerValue().multiply(that.bigIntegerValue()));
    }

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
    public Sorting sortWith(final Number that) {
        if (that instanceof NonNegativeInteger) {
            return this.compareTo((NonNegativeInteger) that);
        } else if (that instanceof BigInteger) {
            return this.compareTo((BigInteger) that);
        } else {
            final double d1 = this.doubleValue();
            final double d2 = that.doubleValue();
            return Sorting.from(Double.compare(d1, d2));
        }
    }

    public Sorting compareTo(final NonNegativeInteger that) {
        return that instanceof Infinity
                ? Sorting.BEFORE
                : this.compareTo(that.bigIntegerValue());
    }

    public Sorting compareTo(final BigInteger that) {
        return Sorting.from(this.bigIntegerValue().compareTo(that));
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Number
                && this.equals((Number) object);
    }

    public boolean equals(final Number that) {
        return that != null
                && this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return this.intValue();
    }

    @Override
    public String toString() {
        return this.bigIntegerValue().toString();
    }

}
