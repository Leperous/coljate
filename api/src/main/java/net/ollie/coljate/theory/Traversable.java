package net.ollie.coljate.theory;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface Traversable<@Nullable T>
        extends Streamable<T>, Iterable<T>, Container {

    @Nullable
    default T head() {
        return this.iterator().next();
    }

    @NonNull
    Traversable<T> tail();

    @Override
    default boolean contains(final Object object) {
        for (final Iterator<?> iterator = this.iterator(); iterator.hasNext();) {
            if (Objects.equals(iterator.next(), object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
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
