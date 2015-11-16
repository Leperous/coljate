package net.ollie.coljate.theory;

import java.util.stream.Stream;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public interface Streamable<@Nullable T> {

    @Deprecated
    default Stream<T> stream() {
        return this.serialStream();
    }

    @NonNull
    Stream<T> serialStream();

    @NonNull
    Stream<T> parallelStream();

}
