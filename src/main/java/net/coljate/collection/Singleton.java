package net.coljate.collection;

import java.util.Objects;

/**
 *
 * @author ollie
 */
public interface Singleton<T>
        extends ImmutableCollection<T> {

    T element();

    @Override
    default boolean contains(final Object object) {
        return Objects.equals(object, this.element());
    }

}
