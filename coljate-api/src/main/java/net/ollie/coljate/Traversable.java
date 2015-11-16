package net.ollie.coljate;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Ollie
 */
public interface Traversable<T> extends Streamable<T>, Iterable<T> {

    default T head() {
        return this.iterator().next();
    }

    Traversable<T> tail();

    default boolean isEmpty() {
        return this.iterator().hasNext();
    }

    @Override
    default Stream<T> serialStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    default Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

}
