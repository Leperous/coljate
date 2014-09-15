package net.ollie.coljate.sets;

import net.ollie.coljate.imposed.Finite;
import net.ollie.coljate.imposed.Finite.Universe;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface FiniteSet<V, U extends Universe<V>>
        extends Set<V>, Finite<V, Universe<V>> {

    @Override
    FiniteSet<V, U> complement();

    @Nonnull
    FiniteSet<V, U> union(FiniteSet<V, U> set);

    @Nonnull
    FiniteSet<V, U> intersection(FiniteSet<V, U> set);

}
