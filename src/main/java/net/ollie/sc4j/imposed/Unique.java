package net.ollie.sc4j.imposed;

import net.ollie.sc4j.Collection;

/**
 * Values are unique, in that they can only exist within this collection zero or one times.
 *
 * @author Ollie
 * @see Duplicated
 */
public interface Unique<V>
        extends Collection<V> {

}
