package net.ollie.sc4j.collections;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Set;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public abstract class ImmutableSet<V>
        extends AbstractDelegatedIteratable<V>
        implements Set.Immutable<V> {

    private static final long serialVersionUID = 1L;

    public static <V> Set.Immutable<V> copyIntoHashSet(final Iterable<V> iterable) {
        return new ImmutableHashSet<>(MutableSet.copyIntoHashSet(iterable));
    }

    public static <V extends Comparable<? super V>> Set.Immutable<V> copyIntoTreeSet(final java.util.Set<V> set) {
        return new ImmutableNaturalTreeSet<>(MutableSet.copyIntoTreeSet(set));
    }

    private final Set<V> underlying;

    protected ImmutableSet(final Set<V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected Set<V> underlying() {
        return underlying;
    }

    @Override
    public Set.Immutable<V> tail() {
        return underlying.tail().immutable();
    }

    @Override
    public Set.Mutable<V> mutable() {
        return underlying.mutable();
    }

    protected abstract <T> Set.Immutable<T> copy(Set.Mutable<T> set);

    @Override
    public Set.Immutable<V> with(final V value) {
        final Set.Mutable<V> copy = this.mutable();
        return copy.add(value)
                ? this.copy(copy)
                : this;
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public Set.Immutable<V> without(final Object object) {
        final Set.Mutable<V> copy = this.mutable();
        return copy.remove(object)
                ? this.copy(copy)
                : this;
    }

    @Override
    public Set.Immutable<V> filter(final Predicate<? super V> predicate) {
        throw new UnsupportedOperationException("filter() not supported.");
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return Iterables.unmodifiable(underlying.iterator());
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof Set
                && this.equals((Set) object);
    }

    @Override
    public int hashCode() {
        return Iterables.hashCode(this);
    }

    @Override
    public <V2> Set.Immutable<V2> map(Function<? super V, ? extends V2> function) {
        throw new UnsupportedOperationException("map() not supported.");
    }

    static final class ImmutableHashSet<V>
            extends ImmutableSet<V> {

        ImmutableHashSet(final Set<V> underlying) {
            super(underlying);
        }

        @Override
        protected <T> Set.Immutable<T> copy(final Set.Mutable<T> set) {
            return new ImmutableHashSet<>(set);
        }

    }

    static final class ImmutableNaturalTreeSet<V>
            extends ImmutableSet<V> {

        ImmutableNaturalTreeSet(final Set<V> underlying) {
            super(underlying);
        }

        @Override
        protected <T> Set.Immutable<T> copy(final Set.Mutable<T> set) {
            return new ImmutableNaturalTreeSet<>(set);
        }

    }

}
