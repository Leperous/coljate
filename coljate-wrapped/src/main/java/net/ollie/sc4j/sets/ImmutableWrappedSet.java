package net.ollie.sc4j.sets;

import net.ollie.sc4j.AbstractWrappedStreamable;
import net.ollie.sc4j.Set;
import net.ollie.sc4j.utils.iterators.Iterators;
import net.ollie.sc4j.utils.iterators.UnmodifiableIterator;

/**
 * @author Ollie
 */
public abstract class ImmutableWrappedSet<V>
        extends AbstractWrappedStreamable<V>
        implements Set.Immutable<V> {

    private static final long serialVersionUID = 1L;

    @Override
    protected abstract Set<V> underlying();

    @Override
    public Set.Immutable<V> tail() {
        return this.underlying().tail().immutableCopy();
    }

    @Override
    public Set.Mutable<V> mutableCopy() {
        return this.underlying().mutableCopy();
    }

    protected abstract Set.Immutable<V> copyOf(Set<V> set);

    @Override
    public Set.Immutable<V> and(final V value) {
        final Set.Mutable<V> copy = this.mutableCopy();
        return copy.add(value)
                ? this.copyOf(copy)
                : this;
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public Set.Immutable<V> not(final Object object) {
        final Set.Mutable<V> copy = this.mutableCopy();
        return copy.remove(object)
                ? this.copyOf(copy)
                : this;
    }

    @Override
    public Set.Stream<V, ? extends Set<V>> stream() {
        return this.underlying().stream();
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return Iterators.unmodifiable(super.iterator());
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
