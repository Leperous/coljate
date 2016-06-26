package net.ollie.coljate.set;

import net.ollie.coljate.AbstractCollection;

/**
 *
 * @author ollie
 */
public abstract class AbstractSet<T>
        extends AbstractCollection<T>
        implements Set<T> {

    @Override
    public boolean equals(final Object object) {
        return object instanceof Set
                && this.equals((Set<?>) object);
    }

    public boolean equals(final Set<?> set) {
        return Set.elementsEqual(this, set);
    }

    @Override
    public int hashCode() {
        return Set.hash(this);
    }

}
