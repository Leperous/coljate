package net.ollie.coljate.utils.iterators;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.coljate.access.Streamable;
import net.ollie.coljate.access.Streamable.Stream;

/**
 *
 * @author Ollie
 */
public final class Streams {

    private Streams() {
    }

    public static <V, S extends Streamable.Empty<V>> Stream<V, S> empty(final S source) {
        return new EmptyStream<>(source);
    }

    private static final class EmptyStream<V, S extends Streamable.Empty<V>>
            extends EmptyIterator<V>
            implements Stream<V, S> {

        final S source;

        EmptyStream(final S empty) {
            this.source = empty;
        }

        @Override
        public Stream<V, S> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        public <V2> Stream<V2, ? extends Streamable.Empty<V2>> map(Function<? super V, ? extends V2> function) {
            return (Stream<V2, Streamable.Empty<V2>>) this;
        }

        @Override
        public <V2> Stream<V2, ? extends Streamable.Empty<V2>> flatMap(Function<? super V, ? extends Streamable<? extends V2>> function) {
            return (Stream<V2, Streamable.Empty<V2>>) this;
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return this;
        }

        @Override
        public S collect() {
            return source;
        }

    }

}
