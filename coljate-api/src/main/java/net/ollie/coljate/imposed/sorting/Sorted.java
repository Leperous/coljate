package net.ollie.coljate.imposed.sorting;

import java.util.Comparator;

import net.ollie.coljate.imposed.Ordered;

import javax.annotation.Nonnull;

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
