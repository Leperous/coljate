package net.ollie.coljate.sets;


import net.ollie.coljate.AbstractNativelyDelegatedStreamable;
import net.ollie.coljate.Set;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableWrappedSet<V>
        extends AbstractNativelyDelegatedStreamable<V>
        implements Set.Mutable<V> {

    @Override
    public Set<V> tail() {
        final Set.Mutable<V> copy = this.mutableCopy();
        copy.remove(this.head());
        return copy;
    }

    @Override
    public boolean add(final V value) {
        return this.delegate().add(value);
    }

    @Override
    public boolean addAll(final Iterable<? extends V> iterable) {
        boolean added = false;
        for (final V value : iterable) {
            added |= this.add(value);
        }
        return added;
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean remove(final Object value) {
        return this.delegate().remove(value);
    }

    @Override
    public boolean removeAll(final Iterable<?> iterable) {
        boolean removed = false;
        for (final Object value : iterable) {
            removed |= this.remove(value);
        }
        return removed;
    }

    @Override
    public void clear() {
        this.delegate().clear();
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Set
                && this.equals((Set<?>) object);
    }

    @Override
    public int hashCode() {
        return this.hash();
    }

}
