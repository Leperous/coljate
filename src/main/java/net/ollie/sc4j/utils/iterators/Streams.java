package net.ollie.sc4j.utils.iterators;

import java.util.function.BiPredicate;
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

    public static <V> Stream<V> empty() {
        return new EmptyStream<>();
    }

    private static final class EmptyStream<V>
            extends EmptyIterator<V>
            implements Stream<V> {

        @Override
        public Stream<V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        public <V2> Stream<V2> map(Function<? super V, ? extends V2> function) {
            return (Stream<V2>) this;
        }

        @Override
        public <V2> Stream<V2> flatMap(Function<? super V, ? extends Streamable<? extends V2>> function) {
            return (Stream<V2>) this;
        }

        @Override
        public Stream<V> unique(final BiPredicate<? super V, ? super V> predicate) {
            return this;
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return this;
        }

    }

}
