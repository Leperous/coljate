package net.ollie.sc4j.utils;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A non-negative number, including zero.
 *
 * @author Ollie
 * @see <a href="http://en.wikipedia.org/wiki/Natural_number">Natural number</a>
 */
public final class NonNegative
        extends Number
        implements Comparable<Number> {

    private static final long serialVersionUID = 1L;
    public static final NonNegative ZERO = new NonNegative(0), ONE = new NonNegative(1);

    public static NonNegative of(final int value) {
        switch (value) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            default:
                return new NonNegative(value);
        }
    }

    public static Number maybe(final int value) {
        return value >= 0
                ? of(value)
                : Integer.valueOf(value);
    }

    private final int value;

    private NonNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative value [" + value + "]!");
        }
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return (double) value;
    }

    @Nonnull
    @CheckReturnValue
    public NonNegative increment() {
        return new NonNegative(value + 1);
    }

    public int peekDecrement() {
        return value - 1;
    }

    public Optional<NonNegative> decrement() {
        final int decremented = this.peekDecrement();
        return decremented >= 0
                ? Optional.of(NonNegative.of(decremented))
                : Optional.empty();
    }

    public boolean isZero() {
        return value == 0;
    }

    @Override
    public int compareTo(final Number that) {
        final double d1 = this.doubleValue();
        final double d2 = that.doubleValue();
        return Double.compare(d1, d2);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
