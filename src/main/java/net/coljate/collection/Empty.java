package net.coljate.collection;

import java.util.function.Function;
import java.util.function.Predicate;

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

    @Override
    default <R> Empty<R> transform(final Function<? super T, ? extends R> transformation) {
        return (Empty<R>) this;
    }

    @Override
    default Empty<T> filter(final Predicate<? super T> predicate) {
        return this;
    }

}
