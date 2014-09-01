package net.ollie.sc4j.utils.iterators;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Streamable;
import net.ollie.sc4j.access.Streamable.Stream;

/**
 *
 * @author Ollie
 */
public final class Streams {

    private Streams() {
    }

    public static <V, C extends Streamable.Empty<V>> Stream.Unmodifiable<V, C> empty(final C empty) {
        return new EmptyStream<>(empty);
    }

    private static final class EmptyStream<V, C extends Streamable.Empty<V>>
            extends EmptyIterator<V>
            implements Stream.Unmodifiable<V, C> {

        private final C empty;

        EmptyStream(final C empty) {
            this.empty = empty;
        }

        @Override
        public Stream<V, C> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        public <V2> Stream<V2, ? extends Streamable<V2>> map(Function<? super V, ? extends V2> function) {
            return (Stream<V2, ? extends Streamable<V2>>) this;
        }

        @Override
        public <V2> Stream<V2, ? extends Streamable<V2>> flatMap(Function<? super V, ? extends Streamable<? extends V2>> function) {
            return (Stream<V2, ? extends Streamable<V2>>) this;
        }

        @Override
        public C collect() {
            return empty;
        }

    }

}
