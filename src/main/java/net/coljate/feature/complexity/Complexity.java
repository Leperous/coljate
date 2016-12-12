package net.coljate.feature.complexity;

/**
 *
 * @author Ollie
 */
public enum Complexity {

    /**
     * Unknown complexity.
     */
    UNKNOWN(Double.NaN),
    /**
     * Complexity {@code O(1)}.
     */
    CONSTANT(0),
    /**
     * Complexity {@code O(ln(n))}.
     */
    LOGARITHMIC(0.5),
    /**
     * Complexity {@code O(n)}.
     */
    LINEAR(1),
    /**
     * Complexity {@code O(n ln(n))}.
     */
    LINEARITHMIC(1.5),
    /**
     * Complexity {@code O(n^2)}.
     */
    QUADRATIC(2),
    /**
     * Complexity {@code O(n^x)}.
     */
    POLYNOMIAL(Double.MAX_VALUE),
    /**
     * Complexity {@code O(2^n)}.
     */
    EXPONENTIAL(Double.POSITIVE_INFINITY);

    private final double pow;

    private Complexity(double pow) {
        this.pow = pow;
    }

    public Complexity times(final Complexity that) {
        if (!Double.isFinite(this.pow)) {
            return this;
        } else if (!Double.isFinite(that.pow)) {
            return that;
        }
        final double pow = this.pow + that.pow;
        for (final Complexity value : values()) {
            if (value.pow >= pow) {
                return value;
            }
        }
        return UNKNOWN;
    }

}
