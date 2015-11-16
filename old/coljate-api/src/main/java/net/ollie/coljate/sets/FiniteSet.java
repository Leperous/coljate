package net.ollie.coljate.sets;

import net.ollie.coljate.imposed.Finite;
import net.ollie.coljate.imposed.Finite.Universe;

/**
 *
 * @author Ollie
 */
public interface FiniteSet<V, U extends Universe<V>>
        extends Set<V>, Finite<V, Universe<V>> {

    @Override
    FiniteSet<V, U> complement();

    @Override
    FiniteSet<V, U> union(Set<? extends V> iterable);

    @Override
    FiniteSet<V, U> intersection(Set<? extends V> iterable);

}
