package net.coljate.collection;

import net.coljate.util.Hash;
import net.coljate.util.Strings;
import net.coljate.list.Ordered;

/**
 *
 * @author ollie
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + Strings.toString(this) + "]";
    }

    @Override
    public boolean equals(final Object that) {
        return this == that
                || (that instanceof AbstractCollection && this.equals((AbstractCollection) that));
    }

    protected abstract boolean equals(AbstractCollection<?> that);

    @Override
    public int hashCode() {
        return this instanceof Ordered
                ? Hash.orderedHash(this)
                : Hash.unorderedHash(this);
    }

}
