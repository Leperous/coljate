package net.coljate.collection;

import net.coljate.util.Hash;
import net.coljate.util.Strings;
import net.coljate.sequence.Sequence;

/**
 *
 * @author ollie
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":[" + Strings.toString(this) + "]";
    }

    @Override
    @SuppressWarnings("override.param.invalid")
    public boolean equals(final Object that) {
        return this == that
                || (that instanceof Collection && this.equals((Collection<?>) that));
    }

    protected abstract boolean equals(Collection<?> that);

    @Override
    public int hashCode() {
        return this instanceof Sequence
                ? Hash.orderedHash(this)
                : Hash.unorderedHash(this);
    }

}
