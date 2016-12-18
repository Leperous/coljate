package net.coljate.collection;

import net.coljate.UnmodifiableIterator;
import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface Empty<T> extends ImmutableCollection<T> {

    @Override
    @TimeComplexity(Complexity.CONSTANT)
    default boolean isEmpty() {
        return true;
    }

    @Override
    @TimeComplexity(Complexity.CONSTANT)
    default boolean contains(final Object object) {
        return false;
    }

    @Override
    default UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.of();
    }

}
