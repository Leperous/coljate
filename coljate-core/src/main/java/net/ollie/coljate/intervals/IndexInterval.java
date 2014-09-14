package net.ollie.coljate.intervals;

import net.ollie.coljate.Set;
import net.ollie.coljate.SortedSet;
import net.ollie.coljate.imposed.sorting.Sortable;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.sets.ImmutableWrappedTreeSet;
import net.ollie.coljate.sets.MutableWrappedTreeSet;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import java.io.Serializable;

/**
 *
 * @author Ollie
 */
public class IndexInterval implements SortedSet<NonNegativeInteger> {

    private static final IndexBound INFINITY = new UpperBound(NonNegativeInteger.INFINITY, true);

    public static IndexInterval lessThanOrEqualTo(final NonNegativeInteger max) {
        return closed(NonNegativeInteger.ZERO, max);
    }

    public static IndexInterval greaterThan(final NonNegativeInteger min) {
        return new IndexInterval(new LowerBound(min, false), INFINITY);
    }

    public static IndexInterval closed(final NonNegativeInteger min, final NonNegativeInteger max) {
        return new IndexInterval(new LowerBound(min, true), new UpperBound(max, true));
    }

    private final IndexBound lower, upper;

    IndexInterval(final IndexBound lower, final IndexBound upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public Bound<NonNegativeInteger> lower() {
        return lower;
    }

    @Override
    public Bound<NonNegativeInteger> upper() {
        return upper;
    }

    @Override
    public Sorter<? super NonNegativeInteger> sorter() {
        return Sorter.natural();
    }

    @Override
    public boolean isEmpty() {
        switch (lower.sortWith(upper)) {
            case AFTER:
                return true;
            case SAME:
                return lower.isInclusive() || upper.isInclusive();
            default:
                return false;
        }
    }

    @Override
    public boolean contains(final Object object) {
        final NonNegativeInteger that = object instanceof Number ? NonNegativeInteger.maybe((Number) object) : null;
        return that == null ? false : (lower.precedes(that) && upper.exceeds(that));
    }

    @Override
    public SortedSet<NonNegativeInteger> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public Stream<NonNegativeInteger, Set<NonNegativeInteger>> stream() {
        throw new UnsupportedOperationException("stream not supported yet!");
    }

    @Override
    public SortedSet.Mutable<NonNegativeInteger> mutableCopy() {
        return MutableWrappedTreeSet.copy(this);
    }

    @Override
    public SortedSet.Immutable<NonNegativeInteger> immutableCopy() {
        return ImmutableWrappedTreeSet.copy(this);
    }

    private static class IndexBound implements Bound<NonNegativeInteger>, Sortable<IndexBound>, Serializable {

        private static final long serialVersionUID = 1L;

        final NonNegativeInteger value;
        final boolean inclusive;

        IndexBound(final NonNegativeInteger bound, final boolean inclusive) {
            this.value = bound;
            this.inclusive = inclusive;
        }

        @Override
        public NonNegativeInteger value() {
            return value;
        }

        boolean isInclusive() {
            return inclusive;
        }

        boolean precedes(final NonNegativeInteger that) {
            switch (value.compareTo(that)) {
                case BEFORE:
                    return true;
                case SAME:
                    return this.isInclusive();
                default:
                    return false;
            }
        }

        boolean exceeds(final NonNegativeInteger that) {
            switch (value.compareTo(that)) {
                case AFTER:
                    return true;
                case SAME:
                    return this.isInclusive();
                default:
                    return false;
            }
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof IndexBound && this.equals((IndexBound) object);
        }

        boolean equals(final IndexBound that) {
            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public Sorting sortWith(final IndexBound that) {
            return value.sortWith(that.value);
        }

    }

    private static final class LowerBound extends IndexBound {

        private static final long serialVersionUID = 1L;

        LowerBound(final NonNegativeInteger bound, final boolean inclusive) {
            super(bound, inclusive);
        }

        @Override
        public String toString() {
            return (this.isInclusive() ? "[" : "(") + this.value();
        }

    }

    private static final class UpperBound extends IndexBound {

        private static final long serialVersionUID = 1L;

        UpperBound(final NonNegativeInteger bound, final boolean inclusive) {
            super(bound, inclusive);
        }

        @Override
        public String toString() {
            return this.value() + (this.isInclusive() ? "]" : ")");
        }

    }

}
