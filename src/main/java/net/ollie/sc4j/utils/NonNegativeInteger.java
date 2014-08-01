package net.ollie.sc4j.utils;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A non-negative integer, including zero.
 *
 * @author Ollie
 * @see <a href="http://en.wikipedia.org/wiki/Natural_number">Natural number</a>
 */
public final class NonNegativeInteger
        extends Number
        implements Comparable<Number> {

    private static final long serialVersionUID = 1L;
    public static final NonNegativeInteger ZERO = new NonNegativeInteger(0), ONE = new NonNegativeInteger(1);

    @Nonnull
    public static NonNegativeInteger of(final int value) {
        switch (value) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            default:
                return new NonNegativeInteger(value);
        }
    }

    @Nonnull
    public static NonNegativeInteger of(final Number number) {
        return number instanceof NonNegativeInteger
                ? (NonNegativeInteger) number
                : of(Math.round(number.floatValue()));
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final int value) {
        return value >= 0
                ? of(value)
                : null;
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final OptionalInt optional) {
        return optional.isPresent()
                ? maybe(optional.getAsInt())
                : null;
    }

    @CheckForNull
    public static NonNegativeInteger maybe(final Number number) {
        return number instanceof NonNegativeInteger
                ? (NonNegativeInteger) number
                : maybe(Math.round(number.floatValue()));
    }

    public static Predicate<NonNegativeInteger> predicate(final Predicate<? super Integer> integerPredicate) {
        return (NonNegativeInteger i) -> integerPredicate.test(i.intValue());
    }

    private final int value;

    private NonNegativeInteger(final int value) {
        Conditions.checkIsNonNegative(value);
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Nonnull
    @CheckReturnValue
    public NonNegativeInteger increment() {
        return new NonNegativeInteger(value + 1);
    }

    public int peekDecrement() {
        return value - 1;
    }

    public Optional<NonNegativeInteger> decrement() {
        final int decremented = this.peekDecrement();
        return decremented >= 0
                ? Optional.of(NonNegativeInteger.of(decremented))
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
    public boolean equals(final Object object) {
        return object instanceof Number
                && this.equals((Number) object);
    }

    public boolean equals(@Nonnull final Number that) {
        return this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
