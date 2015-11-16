package net.ollie.coljate.theory;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface Traversable<@Nullable T> extends Streamable<T>, Iterable<T> {

    @Nullable
    default T head() {
        return this.iterator().next();
    }

    @NonNull
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
