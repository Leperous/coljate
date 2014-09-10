package net.ollie.coljate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.lists.AbstactMutableWrappedArray;
import net.ollie.coljate.lists.ImmutableWrappedArray;
import net.ollie.coljate.streams.DefaultStream;
import net.ollie.coljate.utils.ArrayLists;

import javax.annotation.Nonnull;


/**
 *
 * @author Ollie
 */
public class StreamableWrapper<V> implements Streamable<V> {

    public static <V> Streamable<V> view(final java.util.Collection<V> collection) {
        return new StreamableWrapper<>(collection);
    }

    public static <V> Streamable<V> create() {
        return view(java.util.Collections.emptySet());
    }

    public static <V> Streamable<V> copy(@Nonnull final Iterable<? extends V> iterable) {
        return view(ArrayLists.copy(iterable));
    }

    @SuppressWarnings("unchecked")
    public static <V> Collector<V, ?, Streamable<V>> collector() {
        return StreamableWrapperCollector.INSTANCE;
    }

    private final java.util.Collection<V> collection;

    protected StreamableWrapper(final java.util.Collection<V> collection) {
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
        return DefaultStream.create(this, () -> StreamableWrapper.collector());
    }

    @Override
    public Iterator<V> iterator() {
        return collection.iterator();
    }

    @Override
    public Array.Mutable<V> mutableCopy() {
        return AbstactMutableWrappedArray.copy(this);
    }

    @Override
    public Array.Immutable<V> immutableCopy() {
        return ImmutableWrappedArray.copy(this);
    }

    protected static class StreamableWrapperCollector<V>
            implements Collector<V, StreamableWrapper<V>, Streamable<V>> {

        static final StreamableWrapperCollector INSTANCE = new StreamableWrapperCollector();

        public StreamableWrapperCollector() {
        }

        @Override
        public Supplier<StreamableWrapper<V>> supplier() {
            return () -> new StreamableWrapper<>(new ArrayList<>());
        }

        @Override
        public BiConsumer<StreamableWrapper<V>, V> accumulator() {
            return (streamable, value) -> streamable.collection.add(value);
        }

        @Override
        public BinaryOperator<StreamableWrapper<V>> combiner() {
            return (l, r) -> {
                l.collection.addAll(r.collection);
                return l;
            };
        }

        @Override
        public Function<StreamableWrapper<V>, Streamable<V>> finisher() {
            return f -> f;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return java.util.Collections.emptySet();
        }

    }

}
