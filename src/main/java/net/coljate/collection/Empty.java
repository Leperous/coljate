package net.coljate.collection;

import net.coljate.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public interface Empty<T> extends ImmutableCollection<T> {

    @Override
    default boolean isEmpty() {
        return true;
    }

    @Override
    default boolean contains(final Object object) {
        return false;
    }

    @Override
    default UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.of();
    }

}
