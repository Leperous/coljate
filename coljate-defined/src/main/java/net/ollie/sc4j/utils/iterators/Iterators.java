package net.ollie.sc4j.utils.iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public final class Iterators {

    private Iterators() {
    }

    @Nonnull
    public static <V> UnmodifiableIterator<V> of() {
        return UnmodifiableIterator.of();
    }

    @Nonnull
    public static <V> UnmodifiableIterator<V> of(@CheckForNull final V element) {
        return element == null ? of() : UnmodifiableIterator.of(element);
    }

    public static <V> Iterator<V> of(final V[] array) {
        return new ArrayIterator<>(array);
    }

    public static <F, T> Iterator<T> transform(final Iterable<F> iterable, final Function<? super F, ? extends T> function) {
        return new TransformedIterator<>(iterable.iterator(), function);
    }

    public static <V> Iterator<V> ofArray(final AtomicReferenceArray<? extends V> array) {
        return new AtomicReferenceArrayIterator<>(array);
    }

    public static <V, A, R> R collect(final Iterator<? extends V> iterator, final Collector<? super V, A, ? extends R> collector) {
        final A into = collector.supplier().get();
        final BiConsumer<A, ? super V> accumulator = collector.accumulator();
        while (iterator.hasNext()) {
            accumulator.accept(into, iterator.next());
        }
        return collector.finisher().apply(into);
    }

    public static <V, R> R reduce(final Iterator<? extends V> iterator, final BiFunction<R, V, ? extends R> function, final R initial) {
        R current = initial;
        while (iterator.hasNext()) {
            current = function.apply(current, iterator.next());
        }
        return current;
    }

    public static <V> Optional<V> findFirst(final Iterator<? extends V> iterator, final Predicate<? super V> predicate) {
        while (iterator.hasNext()) {
            final V next = iterator.next();
            if (predicate.test(next)) {
                return Optional.of(next);
            }
        }
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public static <V> UnmodifiableIterator<V> unmodifiable(@Nonnull final Iterator<? extends V> iterator) {
        return UnmodifiableIterator.of(iterator);
    }

    private static final class ArrayIterator<V>
            implements Iterator<V> {

        final V[] array;
        int index;

        ArrayIterator(final V[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public V next() {
            return array[index++];
        }

    }

    private static final class TransformedIterator<F, T>
            implements Iterator<T> {

        final Iterator<F> iterator;
        final Function<? super F, ? extends T> function;

        TransformedIterator(final Iterator<F> iterator, final Function<? super F, ? extends T> function) {
            this.iterator = iterator;
            this.function = function;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return function.apply(iterator.next());
        }

        @Override
        public void remove() {
            iterator.remove();
        }

    }

    private static final class AtomicReferenceArrayIterator<V>
            implements Iterator<V> {

        private final AtomicReferenceArray<? extends V> array;
        private int index;

        AtomicReferenceArrayIterator(final AtomicReferenceArray<? extends V> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length();
        }

        @Override
        public V next() {
            return array.get(index++);
        }

    }

}
