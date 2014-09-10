package net.ollie.sc4j.lists;


import net.ollie.sc4j.AbstractStreamable;
import net.ollie.sc4j.List;

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
