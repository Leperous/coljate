package net.coljate;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author ollie
 */
public interface StreamExtension<T> extends Iterable<T> {

    @Deprecated
    default Stream<T> stream() {
        return this.serialStream();
    }

    default Stream<T> serialStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

}
