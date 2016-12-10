package net.coljate.set;

import net.coljate.collection.AbstractCollection;
import net.coljate.util.Hash;

/**
 *
 * @author ollie
 */
public abstract class AbstractSet<T>
        extends AbstractCollection<T>
        implements Set<T> {

    @Override
    public abstract boolean contains(Object object);

    @Override
    public boolean equals(final Object that) {
        return super.equals(that);
    }

    @Override
    protected boolean equals(final AbstractCollection<?> that) {
        return that instanceof Set
                && this.equals((Set) that);
    }

    protected abstract boolean equals(Set<?> that);

    @Override
    public int hashCode() {
        return Hash.unorderedHash(this);
    }

}
