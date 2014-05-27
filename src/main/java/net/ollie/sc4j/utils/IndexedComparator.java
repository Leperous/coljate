package net.ollie.sc4j.utils;

import java.util.OptionalInt;

import net.ollie.sc4j.access.Indexed;

/**
 *
 * @author Ollie
 */
public class IndexedComparator<V>
        implements java.util.Comparator<V> {

    private final Indexed<? extends V> indexed;

    public IndexedComparator(final Indexed<? extends V> indexed) {
        this.indexed = indexed;
    }

    @Override
    public int compare(final V o1, final V o2) {
        final OptionalInt i1 = indexed.indexOf(o1);
        final OptionalInt i2 = indexed.indexOf(o2);
        if (i1.isPresent() && i2.isPresent()) {
            return Integer.compare(i1.getAsInt(), i2.getAsInt());
        } else if (i1.isPresent()) {
            return +1;
        } else if (i2.isPresent()) {
            return -1;
        } else {
            return 0;
        }
    }

}
