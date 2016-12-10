package net.coljate.list;

import net.coljate.collection.AbstractCollection;
import net.coljate.util.Hash;

/**
 *
 * @author ollie
 */
public abstract class AbstractList<T>
        extends AbstractCollection<T>
        implements List<T> {

    @Override
    public boolean equals(final Object that) {
        return super.equals(that);
    }

    @Override
    protected boolean equals(final AbstractCollection<?> that) {
        return that instanceof AbstractList
                && this.elementsEqual((List) that);
    }

    @Override
    public int hashCode() {
        return Hash.orderedHash(this);
    }

}
