package net.ollie.coljate.intervals;

import net.ollie.coljate.SortedSet;
import net.ollie.coljate.imposed.sorting.Sortable;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.sets.ImmutableWrappedTreeSet;
import net.ollie.coljate.sets.WrappedTreeSetBuilder;
import net.ollie.coljate.utils.iterators.Streams;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import java.io.Serializable;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class IndexInterval
        extends WrappedTreeSetBuilder<NonNegativeInteger>
        implements SortedSet.Immutable<NonNegativeInteger>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final IndexBound INFINITY = new UpperBound(NonNegativeInteger.INFINITY, true);
    private static final EmptyIndexInterval EMPTY = new EmptyIndexInterval();

    public static EmptyIndexInterval empty() {
        return EMPTY;
    }

    @Nonnull
    public static IndexInterval lessThanOrEqualTo(final NonNegativeInteger max) {
        return closed(NonNegativeInteger.ZERO, max);
    }

    @Nonnull
    public static IndexInterval lessThan(final NonNegativeInteger max) {
        return new IndexInterval(new LowerBound(NonNegativeInteger.ZERO, true), new UpperBound(max, false));
    }

    @Nonnull
    public static IndexInterval greaterThan(final NonNegativeInteger min) {
        return new IndexInterval(new LowerBound(min, false), INFINITY);
    }

    @Nonnull
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
    public NonNegativeInteger count() {
        return upper.value().minus(lower.value()).orElse(NonNegativeInteger.ZERO);
    }

    @Override
    public boolean contains(final Object object) {
        final NonNegativeInteger that = object instanceof Number ? NonNegativeInteger.maybe((Number) object) : null;
        return this.contains(that);
    }

    public boolean contains(final NonNegativeInteger that) {
        return that == null ? false : (lower.precedes(that) && upper.exceeds(that));
    }

    @Override
    public SortedSet.Immutable<NonNegativeInteger> and(final NonNegativeInteger value) {
        return this.contains(value)
                ? this
                : ImmutableWrappedTreeSet.copy(this).and(value);
    }

    @Override
    public SortedSet.Immutable<NonNegativeInteger> not(final Object object) {
        return this.contains(object)
                ? ImmutableWrappedTreeSet.copy(this).not(object)
                : this;
    }

    @Override
    public SortedSet.Immutable<NonNegativeInteger> sort(final Sorter<? super NonNegativeInteger> comparator) {
        return ImmutableWrappedTreeSet.copy(this, comparator);
    }

    @Override
    public Stream<NonNegativeInteger, ? extends IndexInterval> stream() {
        throw new UnsupportedOperationException("stream not supported yet!");
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

    public static final class EmptyIndexInterval extends IndexInterval implements SortedSet.Empty<NonNegativeInteger> {

        private static final long serialVersionUID = 1L;
        static final IndexBound OPEN_ZERO = new IndexBound(NonNegativeInteger.ZERO, false);

        EmptyIndexInterval() {
            super(OPEN_ZERO, OPEN_ZERO);
        }

        @Override
        public SortedSet.Singleton<NonNegativeInteger> and(final NonNegativeInteger element) {
            return ImmutableWrappedTreeSet.create(element);
        }

        @Override
        public EmptyIndexInterval tail() {
            return this;
        }

        @Override
        public SortedSet.Empty<NonNegativeInteger> immutableCopy() {
            return this.emptyCopy();
        }

        @Override
        public Stream<NonNegativeInteger, EmptyIndexInterval> stream() {
            return Streams.empty(this);
        }

        @Override
        public SortedSet.Empty<NonNegativeInteger> not(final Object object) {
            return this;
        }

        @Override
        public SortedSet.Empty<NonNegativeInteger> sort(final Sorter<? super NonNegativeInteger> comparator) {
            return ImmutableWrappedTreeSet.create(comparator);
        }

    }

}
