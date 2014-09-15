package net.ollie.coljate.imposed;

import net.ollie.coljate.imposed.Finite.Universe;
import net.ollie.coljate.utils.numeric.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Finite<V, U extends Universe<V>> {

    @Nonnull
    U universe();

    @Nonnull
    Finite<V, U> complement();

    interface Universe<V> {

        @Nonnull
        NonNegativeInteger cardinality();

    }

}
