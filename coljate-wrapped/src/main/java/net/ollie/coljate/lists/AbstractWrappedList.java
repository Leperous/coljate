package net.ollie.coljate.lists;


import net.ollie.coljate.AbstractStreamable;
import net.ollie.coljate.List;

/**
 *
 * @author Ollie
 */
public abstract class AbstractWrappedList<V>
        extends AbstractStreamable<V>
        implements List<V> {

    @Override
    public List.Mutable<V> mutableCopy() {
        return MutableWrappedLinkedList.copy(this);
    }

    @Override
    public List.Immutable<V> immutableCopy() {
        return ImmutableWrappedList.copy(this);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof List
                && this.equals((List<?>) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

}
