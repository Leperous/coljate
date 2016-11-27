package net.coljate.extend;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author ollie
 */
public interface StreamExtension<T> extends Iterable<T> {

    default Stream<T> serialStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

}
