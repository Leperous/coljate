package net.ollie.sc4j.intervals;

import net.ollie.sc4j.Interval;

/**
 *
 * @author Ollie
 */
public class DoubleInterval
        implements Interval<Double> {

    private static final boolean CLOSED = true;

    public static Interval<Double> degenerate(final double d) {
        return new Degenerate(d);
    }

    public static Interval<Double> closed(final double min, final double max) {
        return new DoubleInterval(min, CLOSED, max, CLOSED);
    }

    public static Interval<Double> open(final double min, final double max) {
        return new DoubleInterval(min, !CLOSED, max, !CLOSED);
    }

    private final double min, max;
    private final boolean minInclusive, maxInclusive;

    protected DoubleInterval(
            final double min,
            final boolean minInclusive,
            final double max,
            final boolean maxInclusive) {
        if (min > max) {
            throw new IllegalArgumentException("Passed " + min + " > " + max);
        }
        this.min = min;
        this.minInclusive = minInclusive;
        this.max = max;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public boolean contains(final Object object) {
        final Double d = this.tryCast(object);
        return d == null ? false : this.contains(d.doubleValue());
    }

    public boolean contains(final double d) {
        return (minInclusive ? d >= min : d > min)
                && (maxInclusive ? d <= max : d < max);
    }

    protected Double tryCast(final Object object) {
        return object instanceof Number
                ? ((Number) object).doubleValue()
                : null;
    }

    @Override
    public boolean isEmpty() {
        return min == max && !this.contains(min);
    }

    @Override
    public Double first() {
        return min;
    }

    @Override
    public Double last() {
        return max;
    }

    @Override
    public boolean firstInclusive() {
        return minInclusive;
    }

    @Override
    public boolean lastInclusive() {
        return maxInclusive;
    }

    @Override
    public String toString() {
        return (minInclusive ? "[" : "(")
                + min
                + ':'
                + max
                + (maxInclusive ? ']' : ')');
    }

    private static final class Degenerate
            extends DoubleInterval {

        private final double value;

        Degenerate(final double value) {
            super(value, true, value, true);
            this.value = value;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public String toString() {
            return "[" + value + "]";
        }

    }

}
