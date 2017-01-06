package net.coljate.collection;

import java.util.Objects;

import net.coljate.list.Ordered;

/**
 *
 * @author ollie
 */
public interface Singleton<T>
        extends ImmutableCollection<T>, Ordered<T> {

    T element();

    @Override
    default boolean contains(final Object object) {
        return Objects.equals(object, this.element());
    }

    @Override
    default int count() {
        return 1;
    }

    @Override
    @Deprecated
    default T first() {
        return this.element();
    }

}
