package net.ollie.coljate;

import net.ollie.coljate.lists.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.lists.MutableWrappedArray;
import net.ollie.coljate.lists.ImmutableWrappedArray;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.ArrayLists;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class DefaultStreamable<V> implements Streamable<V> {

    @Nonnull
    public static <V> Streamable<V> view(final java.util.Collection<V> collection) {
        return new DefaultStreamable<>(collection);
    }

    @Nonnull
    public static <V> Streamable<V> create() {
        return view(java.util.Collections.emptySet());
    }

    @Nonnull
    public static <V> Streamable<V> copy(@Nonnull final Iterable<? extends V> iterable) {
        return view(ArrayLists.copy(iterable));
    }

    @SuppressWarnings("unchecked")
    public static <V> Collector<V, ?, Streamable<V>> collector() {
        return StreamableWrapperCollector.INSTANCE;
    }

    private final java.util.Collection<V> collection;

    protected DefaultStreamable(final java.util.Collection<V> collection) {
        this.collection = collection;
    }

    @Override
    public Object[] toRawArray() {
        return collection.toArray();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return collection.contains(object);
    }

    @Override
    public Streamable<V> tail() {
        final java.util.List<V> copy = ArrayLists.copy(collection);
        copy.remove(0);
        return view(copy); //TODO make more efficient
    }

    @Override
    @SuppressWarnings("Convert2Lambda")
    public Stream<V, Streamable<V>> stream() {
        return DefaultStream.create(this);
    }

    @Override
    public Iterator<V> iterator() {
        return collection.iterator();
    }

    @Override
    public Array.Mutable<V> mutableCopy() {
        return MutableWrappedArray.copy(this);
    }

    @Override
    public Array.Immutable<V> immutableCopy() {
        return ImmutableWrappedArray.copy(this);
    }

    protected static class StreamableWrapperCollector<V>
            implements Collector<V, DefaultStreamable<V>, Streamable<V>> {

        static final StreamableWrapperCollector INSTANCE = new StreamableWrapperCollector();

        public StreamableWrapperCollector() {
        }

        @Override
        public Supplier<DefaultStreamable<V>> supplier() {
            return () -> new DefaultStreamable<>(new ArrayList<>());
        }

        @Override
        public BiConsumer<DefaultStreamable<V>, V> accumulator() {
            return (streamable, value) -> streamable.collection.add(value);
        }

        @Override
        public BinaryOperator<DefaultStreamable<V>> combiner() {
            return (l, r) -> {
                l.collection.addAll(r.collection);
                return l;
            };
        }

        @Override
        public Function<DefaultStreamable<V>, Streamable<V>> finisher() {
            return f -> f;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return java.util.Collections.emptySet();
        }

    }

}
