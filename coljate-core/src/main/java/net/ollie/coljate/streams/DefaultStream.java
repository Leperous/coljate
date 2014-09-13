package net.ollie.coljate.streams;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

import net.ollie.coljate.DefaultStreamable;
import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.utils.iterators.Iterables;
import net.ollie.coljate.utils.iterators.Iterators;
import net.ollie.coljate.utils.iterators.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public abstract class DefaultStream<V, S extends Streamable<V>>
        implements Streamable.Stream<V, S> {

    public static <V, S extends Streamable<V>> DefaultStream<V, S> singleton(final V value, final Supplier<Collector<V, ?, ? extends S>> collectorSupplier) {
        return create(Iterators.of(value), collectorSupplier);
    }

    public static <V> DefaultStream<V, Streamable<V>> create(final Iterable<V> iterable) {
        return create(iterable.iterator(), DefaultStreamable::collector);
    }

    public static <V, S extends Streamable<V>> DefaultStream<V, S> create(final Iterable<V> iterable, final Supplier<Collector<V, ?, ? extends S>> collectorSupplier) {
        return create(iterable.iterator(), collectorSupplier);
    }

    public static <V, S extends Streamable<V>> DefaultStream<V, S> create(final Iterator<? extends V> iterator, final Supplier<Collector<V, ?, ? extends S>> collectorSupplier) {
        return new SimpleStream<>(UnmodifiableIterator.of(iterator), collectorSupplier);
    }

    @Override
    public Streamable.Stream<V, S> filter(final Predicate<? super V> predicate) {
        return new FilteredStream<>(this, predicate);
    }

    @Override
    public <V2> Streamable.Stream<V2, ? extends Streamable<V2>> map(final Function<? super V, ? extends V2> function) {
        return new MappedStream<>(this, function);
    }

    @Override
    public <V2> Streamable.Stream<V2, ? extends Streamable<V2>> flatMap(final Function<? super V, ? extends Streamable<? extends V2>> function) {
        throw new UnsupportedOperationException("flatMap not supported yet!");
    }

    protected abstract Collector<V, ?, ? extends S> getCollector();

    @Override
    public S collect() {
        return Iterables.collect(this, this.getCollector());
    }

    private static class SimpleStream<V, S extends Streamable<V>>
            extends DefaultStream<V, S> {

        private final UnmodifiableIterator<V> iterator;
        private final Supplier<Collector<V, ?, ? extends S>> supplier;

        SimpleStream(final UnmodifiableIterator<V> source, final Supplier<Collector<V, ?, ? extends S>> supplier) {
            this.iterator = source;
            this.supplier = supplier;
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return iterator;
        }

        @Override
        protected Collector<V, ?, ? extends S> getCollector() {
            return supplier.get();
        }

    }

    private static class FilteredStream<V, S extends Streamable<V>>
            extends DefaultStream<V, S> {

        private final DefaultStream<V, S> stream;
        private final Predicate<? super V> predicate;

        FilteredStream(final DefaultStream<V, S> stream, final Predicate<? super V> predicate) {
            this.stream = stream;
            this.predicate = predicate;
        }

        @Override
        protected Collector<V, ?, ? extends S> getCollector() {
            return stream.getCollector();
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return new UnmodifiableIterator<V>() {

                final Iterator<V> source = stream.iterator();
                V next;

                @Override
                public boolean hasNext() {
                    while (source.hasNext()) {
                        final V next = source.next();
                        if (predicate.test(next)) {
                            this.next = next;
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public V next() {
                    if (next == null) {
                        throw new NoSuchElementException();
                    }
                    return next;

                }

            };
        }

    }

    private static final class MappedStream<V, V2>
            extends DefaultStream<V2, Streamable<V2>> {

        private final DefaultStream<V, ?> stream;
        private final Function<? super V, ? extends V2> function;

        MappedStream(final DefaultStream<V, ?> stream, final Function<? super V, ? extends V2> function) {
            this.stream = stream;
            this.function = function;
        }

        @Override
        protected Collector<V2, ?, ? extends Streamable<V2>> getCollector() {
            return DefaultStreamable.collector();
        }

        @Override
        public UnmodifiableIterator<V2> iterator() {
            return new UnmodifiableIterator<V2>() {

                final Iterator<V> source = stream.iterator();

                @Override
                public boolean hasNext() {
                    return source.hasNext();
                }

                @Override
                public V2 next() {
                    return function.apply(source.next());
                }

            };
        }

    }

    private static class UniqueStream<V, S extends Streamable<V>>
            extends DefaultStream<V, S> {

        private final DefaultStream<V, S> stream;
        private final BiPredicate<? super V, ? super V> predicate;

        UniqueStream(final DefaultStream<V, S> stream, final BiPredicate<? super V, ? super V> predicate) {
            this.stream = stream;
            this.predicate = predicate;
        }

        @Override
        protected Collector<V, ?, ? extends S> getCollector() {
            return stream.getCollector();
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return new UnmodifiableIterator<V>() {

                final Iterator<V> source = stream.iterator();
                V next;

                @Override
                public boolean hasNext() {
                    while (source.hasNext()) {
                        final V next = source.next();
                    }
                    throw new UnsupportedOperationException("hasNext not supported yet!");
                }

                @Override
                public V next() {
                    if (next == null) {
                        throw new NoSuchElementException();
                    }
                    final V value = next;
                    next = null;
                    return value;
                }

            };
        }

    }

}
