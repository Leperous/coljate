package net.ollie.sc4j.utils;

import net.ollie.sc4j.access.Indexed;

/**
 *
 * @author Ollie
 */
public class IndexedComparator<V>
        implements Comparator<V> {

    private final Indexed<? extends V> indexed;

    public IndexedComparator(final Indexed<? extends V> indexed) {
        this.indexed = indexed;
    }

    @Override
    public Comparison comparison(final V o1, final V o2) {
        final NonNegativeInteger i1 = indexed.indexOf(o1);
        final NonNegativeInteger i2 = indexed.indexOf(o2);
        if (i1 != null && i2 != null) {
            return Comparison.from(Integer.compare(i1.intValue(), i2.intValue()));
        } else if (i1 != null) {
            return Comparison.AFTER;
        } else if (i2 != null) {
            return Comparison.BEFORE;
        } else {
            return Comparison.SAME;
        }
    }

}
