package net.ollie.coljate.theory;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Traversable<T> extends Streamable<T>, Iterable<T> {

    @CheckForNull
    default T head() {
        return this.iterator().next();
    }

    @Nonnull
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
