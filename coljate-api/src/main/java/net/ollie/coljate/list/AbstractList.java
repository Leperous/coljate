package net.ollie.coljate.list;

import net.ollie.coljate.AbstractCollection;
import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 * @see java.util.AbstractList
 */
public abstract class AbstractList<T>
        extends AbstractCollection<T>
        implements List<T> {

    @Override
    public boolean equals(final Object object) {
        return object instanceof List
                && this.equals((List) object);
    }

    public boolean equals(final List<?> that) {
        return Finite.sequenceEquals(this, that);
    }

    @Override
    public int hashCode() {
        return Finite.productHash(this);
    }

}
