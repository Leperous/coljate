package net.ollie.coljate;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Streamable<T> {

    @Deprecated
    default Stream<T> stream() {
        return this.serialStream();
    }

    @Nonnull
    Stream<T> serialStream();

    @Nonnull
    Stream<T> parallelStream();

}
