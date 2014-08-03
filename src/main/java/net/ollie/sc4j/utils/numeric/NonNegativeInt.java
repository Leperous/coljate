package net.ollie.sc4j.utils.numeric;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.sc4j.utils.Conditions;

import java.math.BigInteger;

/**
 * A non-negative integer, including zero.
 *
 * @author Ollie
 * @see <a href="http://en.wikipedia.org/wiki/Natural_number">Natural number</a>
 */
public final class NonNegativeInt
        extends NonNegativeInteger {

    private static final long serialVersionUID = 1L;

    @Nonnull
    public static NonNegativeInteger of(final int value) {
        switch (value) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            default:
                return new NonNegativeInt(value);
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
    private transient BigInteger bigInt;

    NonNegativeInt(final int value) {
        Conditions.checkIsNonNegative(value);
        this.value = value;
    }

    @Override
    public BigInteger bigIntegerValue() {
        if (bigInt == null) {
            BigInteger.valueOf(value);
        }
        return bigInt;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public NonNegativeInteger increment() {
        return new NonNegativeInt(value + 1);
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
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
