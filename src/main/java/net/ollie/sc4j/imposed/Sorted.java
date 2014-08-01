package net.ollie.sc4j.imposed;

import java.util.Comparator;

import javax.annotation.Nonnull;

/**
 * Values are explicitly sorted according to some {@link Comparator}.
 *
 * @author Ollie
 * @see Ordered
 */
public interface Sorted<V>
        extends Ordered<V> {

    @Nonnull
    Comparator<? super V> comparator();

}
