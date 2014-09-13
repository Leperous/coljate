package net.ollie.coljate.lists;

import java.util.Objects;
import java.util.stream.Collector;

import net.ollie.coljate.AbstractWrappedStreamable;
import net.ollie.coljate.Array;
import net.ollie.coljate.List;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.Arrays;
import net.ollie.coljate.utils.iterators.Iterators;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public class ImmutableWrappedList<V>
        extends AbstractWrappedStreamable<V>
        implements List.Immutable<V> {

    @SuppressWarnings("unchecked")
    @Nonnull
    public static <V> List.Empty<V> create() {
        return ImmutableEmptyList.INSTANCE;
    }

    @Nonnull
    public static <V> List.Singleton<V> create(final V value) {
        return new ImmutableSingletonList<>(value);
    }

    @SafeVarargs
    public static <V> List.Immutable<V> create(final V... values) {
        switch (values.length) {
            case 0:
                return create();
            case 1:
                return create(values[0]);
            default:
                return copy(Arrays.asList(values));
        }
    }

    @SuppressWarnings("unchecked") //Cast is fine as immutable
    public static <V> List.Immutable<V> copy(final Iterable<? extends V> iterable) {
        return iterable instanceof List.Immutable
                ? (List.Immutable<V>) iterable
                : view(MutableWrappedArray.copy(iterable));
    }

    @Nonnull
    private static <V> List.Immutable<V> view(final List.Mutable<V> list) {
        return list.isEmpty()
                ? create()
                : new ImmutableWrappedList<>(list);
    }

    @Nonnull
    public static <V> Collector<V, ?, ? extends List<V>> collector() {
        return new ImmutableListCollector<>(MutableWrappedLinkedList::create);
    }

    private final List<V> underlying;

    protected ImmutableWrappedList(final List<V> underlying) {
        this.underlying = underlying;
    }

    @Override
    protected List<V> underlying() {
        return underlying;
    }

    @Override
    public List.Mutable<V> mutableCopy() {
        return this.underlying().mutableCopy();
    }

    @Override
    public List.Immutable<V> tail() {
        return this.underlying().tail().immutableCopy();
    }

    @Override
    public List.Immutable<V> andPrefix(final V value) {
        final List.Mutable<V> copy = this.mutableCopy();
        copy.prefix(value);
        return view(copy);
    }

    @Override
    public List.Immutable<V> andSuffix(final V value) {
        final List.Mutable<V> copy = this.mutableCopy();
        copy.suffix(value);
        return view(copy);
    }

    @Override
    public List.Immutable<V> notFirst(final Object value) {
        final List.Mutable<V> copy = this.mutableCopy();
        return copy.removeFirst(value)
                ? view(copy)
                : this;
    }

    @Override
    public List.Immutable<V> notAll(final Object value) {
        final List.Mutable<V> copy = this.mutableCopy();
        return copy.removeEvery(value).isZero()
                ? this
                : view(copy);
    }

    @Override
    public UnmodifiableIterator<V> iterator() {
        return Iterators.unmodifiable(super.iterator());
    }

    @Override
    public Stream<V, ? extends List<V>> stream() {
        return DefaultStream.create(this, ImmutableWrappedList::collector);
    }

    @Override
    public V first() {
        return this.underlying().first();
    }

    @Override
    public V last() {
        return this.underlying().last();
    }

    public Array.Immutable<V> toArray() {
        return ImmutableWrappedArray.copy(this.underlying());
    }

    @Override
    public List.Immutable<V> reverse() {
        return this.underlying().reverse().immutableCopy();
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

    protected static class ImmutableEmptyList<V>
            implements List.Empty<V> {

        static final ImmutableEmptyList INSTANCE = new ImmutableEmptyList();

        @Override
        public List.Singleton<V> andPrefix(final V value) {
            return create(value);
        }

        @Override
        public List.Mutable<V> mutableCopy() {
            return MutableWrappedLinkedList.create();
        }

    }

    protected static class ImmutableSingletonList<V>
            implements List.Singleton<V> {

        private final V value;

        protected ImmutableSingletonList(final V value) {
            this.value = value;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public List.Immutable<V> andPrefix(final V value) {
            return create(value, this.value());
        }

        @Override
        public List.Immutable<V> andSuffix(final V value) {
            return create(this.value(), value);
        }

        @Override
        public List.Immutable<V> notFirst(Object value) {
            return Objects.equals(this.value(), value)
                    ? create()
                    : this;
        }

        @Override
        public List.Empty<V> tail() {
            return create();
        }

        @Override
        public List.Mutable<V> mutableCopy() {
            return MutableWrappedLinkedList.create(this.value());
        }

        @Override
        public Stream<V, ? extends List<V>> stream() {
            return DefaultStream.singleton(this.value(), ImmutableWrappedList::collector);
        }

    }

}
