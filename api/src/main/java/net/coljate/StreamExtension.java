package net.coljate;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @since 1.0
 */
public interface StreamExtension<T> extends Iterable<T> {

    @Deprecated
    default Stream<T> stream() {
        return this.serialStream();
    }

    @Nonnull
    default Stream<T> stream(final boolean parallel) {
        return parallel ? this.parallelStream() : this.serialStream();
    }

    @Nonnull
    default Stream<T> serialStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Nonnull
    default Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

}
