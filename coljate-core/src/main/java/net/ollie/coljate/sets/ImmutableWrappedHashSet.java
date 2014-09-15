package net.ollie.coljate.sets;

import java.util.Objects;
import java.util.stream.Collector;

import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.iterators.Iterators;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import javax.annotation.Nonnull;

/**
 * @author Ollie
 * @see MutableWrappedHashSet
 */
public class ImmutableWrappedHashSet<V>
        extends ImmutableWrappedSet<V> {

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V> Set.Empty<V> create() {
        return ImmutableEmptyHashSet.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V> Set.Singleton<V> create(final V value) {
        return new ImmutableSingletonHashSet<>(value);
    }

    @SafeVarargs
    @Nonnull
    public static <V> Set.Immutable<V> create(final V... array) {
        switch (array.length) {
            case 0:
                return create();
            case 1:
                return create(array[0]);
            default:
                return new ImmutableWrappedHashSet<>(MutableWrappedHashSet.create(array));
        }
    }

    /**
     * Copy the given iterable into a new immutable hash set, unless it is already an immutable hash set.
     *
     * @param <V>
     * @param iterable
     * @return
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V> ImmutableWrappedHashSet<V> copy(final Iterable<? extends V> iterable) {
        return iterable instanceof ImmutableWrappedHashSet
                ? (ImmutableWrappedHashSet<V>) iterable
                : new ImmutableWrappedHashSet<>(MutableWrappedHashSet.copy(iterable));
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V> Collector<V, ?, ImmutableWrappedHashSet<V>> collector() {
        return new ImmutableSetCollector<>(MutableWrappedHashSet::create, ImmutableWrappedHashSet::copy);
    }

    private final Set<V> underlying;

    protected ImmutableWrappedHashSet(final Set<V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected Set<V> underlying() {
        return underlying;
    }

    @Override
    protected Set.Immutable<V> copyOf(final Set<V> set) {
        return new ImmutableWrappedHashSet<>(set);
    }

    protected static class ImmutableEmptyHashSet<V>
            implements Set.Empty<V> {

        @SuppressWarnings("rawtypes")
        static final ImmutableEmptyHashSet INSTANCE = new ImmutableEmptyHashSet();

        @Override
        public Set.Singleton<V> and(final V element) {
            return ImmutableWrappedHashSet.create(element);
        }

        @Override
        public Set.Mutable<V> mutableCopy() {
            return MutableWrappedHashSet.create();
        }

    }

    protected static class ImmutableSingletonHashSet<V>
            implements Set.Singleton<V> {

        private final V value;

        protected ImmutableSingletonHashSet(final V value) {
            this.value = value;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public Set.Immutable<V> and(final V value) {
            return Objects.equals(this.value, value)
                    ? this
                    : create(this.value, value);
        }

        @Override
        public Set.Immutable<V> not(final Object value) {
            return Objects.equals(this.value, value)
                    ? create()
                    : this;
        }

        @Override
        public Set.Empty<V> tail() {
            return ImmutableWrappedHashSet.create();
        }

        @Override
        public Set.Mutable<V> mutableCopy() {
            return MutableWrappedHashSet.create(value);
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return Iterators.of(value);
        }

        @Override
        public Stream<V, ? extends Set.Immutable<V>> stream() {
            return DefaultStream.<V, ImmutableWrappedHashSet<V>>create(this, ImmutableWrappedHashSet::collector);
        }

    }

}
