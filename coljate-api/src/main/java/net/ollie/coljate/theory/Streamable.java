package net.ollie.coljate.theory;

import java.util.stream.Stream;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 * @param <T>
 */
public interface Streamable<@Nullable T> {

    /**
     *
     * @return a serial stream.
     * @deprecated use explicit stream type.
     */
    @Deprecated
    default Stream<T> stream() {
        return this.serialStream();
    }

    @NonNull
    Stream<T> serialStream();

    @NonNull
    Stream<T> parallelStream();

}
