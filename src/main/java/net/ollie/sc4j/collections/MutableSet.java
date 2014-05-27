package net.ollie.sc4j.collections;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Set;
import net.ollie.sc4j.utils.HashSets;

/**
 *
 * @author Ollie
 */
public class MutableSet<V>
        implements Set.Mutable<V> {

    public static <V> Set.Mutable<V> view(final java.util.Set<V> set) {
        return new MutableSet<>(set);
    }

    public static <V> Set.Mutable<V> copyIntoHashSet(final Iterable<? extends V> set) {
        return new MutableSet<>(HashSets.copy(set));
    }

    public static <V extends Comparable<? super V>> Set.Mutable<V> copyIntoTreeSet(final java.util.Set<? extends V> set) {
        return new MutableNaturalTreeSet<>(new java.util.TreeSet<>(set));
    }

    private final java.util.Set<V> underlying;

    protected MutableSet(final java.util.Set<V> underlying) {
        this.underlying = underlying;
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    protected java.util.Set<V> underlying() {
        return underlying;
    }

    @Override
    public Set<V> tail() {
        final Set.Mutable<V> copy = this.mutable();
        copy.remove(this.head());
        return copy;
    }

    @Override
    public Set.Mutable<V> mutable() {
        return MutableSet.copyIntoHashSet(this.underlying());
    }

    @Override
    public Set.Immutable<V> immutable() {
        return ImmutableSet.copyIntoHashSet(this.underlying());
    }

    @Override
    public boolean add(final V value) {
        return underlying.add(value);
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
        return underlying.remove(value);
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
    public <V2> Set.Mutable<V2> map(final Function<? super V, ? extends V2> function) {
        throw new UnsupportedOperationException("map() not supported.");
    }

    @Override
    public net.ollie.sc4j.Set<V> filter(Predicate<? super V> predicate) {
        throw new UnsupportedOperationException("filter() not supported.");
    }

    @Override
    public void clear() {
        this.underlying().clear();
    }

    @Override
    public Iterator<V> iterator() {
        return this.underlying().iterator();
    }

    @Override
    public Object[] toRawArray() {
        return this.underlying().toArray();
    }

    static class MutableNaturalTreeSet<V extends Comparable<? super V>>
            extends MutableSet<V> {

        MutableNaturalTreeSet(final java.util.TreeSet<V> underlying) {
            super(underlying);
        }

        @Override
        public Set.Immutable<V> immutable() {
            return super.immutable(); //TODO immutbale tree set
        }

        @Override
        public Set.Mutable<V> mutable() {
            return MutableSet.copyIntoTreeSet(this.underlying());
        }

    }

}
