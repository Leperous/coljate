package net.ollie.sc4j.imposed.sorting;

import java.util.Comparator;

import javax.annotation.Nonnull;

import net.ollie.sc4j.imposed.Ordered;

/**
 * Values are explicitly sorted according to some {@link Comparator}.
 *
 * @author Ollie
 * @see Ordered
 * @see Sortable
 * @see Sorter
 */
public interface Sorted<V>
        extends Ordered<V> {

    @Nonnull
    Sorter<? super V> sorter();

}
