package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;

/**
 * Marker interface to indicate that a value {@code V} can only exist within
 * this collection zero or one times.
 *
 * @author Ollie
 * @see Duplicated
 */
public interface Unique<V>
        extends Collection<V> {

}
