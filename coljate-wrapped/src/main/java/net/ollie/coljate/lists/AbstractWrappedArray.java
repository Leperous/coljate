package net.ollie.coljate.lists;


import net.ollie.coljate.Array;

/**
 *
 * @author Ollie
 */
public abstract class AbstractWrappedArray<V>
        extends AbstractWrappedList<V>
        implements Array<V> {

    @Override
    public Array.Mutable<V> mutableCopy() {
        return AbstactMutableWrappedArray.copy(this);
    }

    @Override
    public Array.Immutable<V> immutableCopy() {
        return ImmutableWrappedArray.copy(this);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Array
                && this.equals((Array<?>) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

}
