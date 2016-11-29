package net.coljate.list;

import net.coljate.util.Hash;

/**
 *
 * @author ollie
 */
public abstract class AbstractList<T> implements List<T> {

    @Override
    public boolean equals(final Object that) {
        return that instanceof List
                && this.equals((List) that);
    }

    @Override
    public int hashCode() {
        return Hash.orderedHash(this);
    }

}
