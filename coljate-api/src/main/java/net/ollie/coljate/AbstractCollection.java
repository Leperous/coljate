package net.ollie.coljate;

import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 * @see java.util.AbstractCollection
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    @Override
    public String toString() {
        return Finite.toString(this);
    }

}
