package net.ollie.sc4j.intervals;

import net.ollie.sc4j.Set;
import net.ollie.sc4j.SortedSet;
import net.ollie.sc4j.imposed.sorting.Sorter;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

/**
 *
 * @author Ollie
 */
public class IndexInterval
        implements SortedSet<NonNegativeInteger> {

    public static IndexInterval zeroToClosed(final NonNegativeInteger max) {
        return closed(NonNegativeInteger.ZERO, max);
    }

    public static IndexInterval closed(final NonNegativeInteger min, final NonNegativeInteger max) {
        return new IndexInterval(min, max);
    }

    private final NonNegativeInteger min, max;

    protected IndexInterval(final NonNegativeInteger min, final NonNegativeInteger max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public NonNegativeInteger last() {
        return max;
    }

    @Override
    public Mutable<NonNegativeInteger> mutableCopy() {
        throw new UnsupportedOperationException("mutableCopy not supported yet!");
    }

    @Override
    public Immutable<NonNegativeInteger> immutableCopy() {
        throw new UnsupportedOperationException("immutableCopy not supported yet!");
    }

    @Override
    public Set<NonNegativeInteger> tail() {
        throw new UnsupportedOperationException("tail not supported yet!");
    }

    @Override
    public Object[] toRawArray() {
        throw new UnsupportedOperationException("toRawArray not supported yet!");
    }

    @Override
    public NonNegativeInteger head() {
        throw new UnsupportedOperationException("head not supported yet!");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("isEmpty not supported yet!");
    }

    @Override
    public boolean contains(Object object) {
        throw new UnsupportedOperationException("contains not supported yet!");
    }

    @Override
    public Stream<NonNegativeInteger, Set<NonNegativeInteger>> stream() {
        throw new UnsupportedOperationException("stream not supported yet!");
    }

    @Override
    public boolean firstInclusive() {
        throw new UnsupportedOperationException("firstInclusive not supported yet!");
    }

    @Override
    public boolean lastInclusive() {
        throw new UnsupportedOperationException("lastInclusive not supported yet!");
    }

    @Override
    public NonNegativeInteger first() {
        throw new UnsupportedOperationException("first not supported yet!");
    }

    @Override
    public Sorter<? super NonNegativeInteger> sorter() {
        throw new UnsupportedOperationException("comparator not supported yet!");
    }

}
