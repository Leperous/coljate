package net.ollie.coljate.intervals;

import net.ollie.coljate.Interval;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class DoubleInterval
        implements Interval<Double>, Serializable {

    private static final long serialVersionUID = 1L;

    public static Interval<Double> degenerate(final double d) {
        final DoubleBound bound = new DoubleBound(d, true);
        return new Degenerate(bound);
    }

    public static Interval<Double> closed(final double min, final double max) {
        return new DoubleInterval(new LowerDoubleBound(min, true), new UpperDoubleBound(max, true));
    }

    public static Interval<Double> greatherThan(final double min) {
        return new DoubleInterval(new LowerDoubleBound(min, false), new UpperDoubleBound(Double.POSITIVE_INFINITY, true));
    }

    public static Interval<Double> open(final double min, final double max) {
        return new DoubleInterval(new LowerDoubleBound(min, false), new UpperDoubleBound(max, false));
    }

    @Nonnull
    private final DoubleBound lower;
    private final DoubleBound upper;

    DoubleInterval(
            final DoubleBound lower,
            final DoubleBound upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public boolean contains(final Object object) {
        final Double d = this.tryCast(object);
        return d == null ? false : this.contains(d.doubleValue());
    }

    @Override
    @Nonnull
    public Bound<Double> lower() {
        return lower;
    }

    @Override
    @Nonnull
    public Bound<Double> upper() {
        return upper;
    }

    public boolean contains(final double d) {
        return lower.precedes(d) && upper.exceeds(d);
    }

    protected Double tryCast(final Object object) {
        return object instanceof Number
                ? ((Number) object).doubleValue()
                : null;
    }

    @Override
    public boolean isEmpty() {
        return lower.value >= upper.value && !this.contains(lower.value);
    }

    @Override
    public String toString() {
        return lower + ":" + upper;
    }

    private static class DoubleBound
            implements Bound<Double>, Serializable {

        private static final long serialVersionUID = 1L;
        final double value;
        final boolean inclusive;

        DoubleBound(final double value, final boolean inclusive) {
            this.value = value;
            this.inclusive = inclusive;
        }

        @Override
        public Double value() {
            return value;
        }

        boolean isInclusive() {
            return inclusive;
        }

        boolean precedes(final double value) {
            return this.value < value || (inclusive && this.value == value);
        }

        boolean exceeds(final double value) {
            return this.value > value || (inclusive && this.value == value);
        }

    }

    static final class LowerDoubleBound extends DoubleBound {

        private static final long serialVersionUID = 1L;

        LowerDoubleBound(final double value, final boolean inclusive) {
            super(value, inclusive);
        }

        @Override
        public String toString() {
            return (this.isInclusive() ? "[" : "(") + this.value();
        }

    }

    static final class UpperDoubleBound extends DoubleBound {

        private static final long serialVersionUID = 1L;

        UpperDoubleBound(double value, boolean inclusive) {
            super(value, inclusive);
        }

        @Override
        public String toString() {
            return this.value() + (this.isInclusive() ? "]" : ")");
        }

    }

    private static final class Degenerate
            extends DoubleInterval {

        private static final long serialVersionUID = 1L;

        Degenerate(final DoubleBound value) {
            super(value, value);
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public String toString() {
            return "[" + this.lower().value() + ']';
        }

    }

}
