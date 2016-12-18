package net.coljate;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;

import net.coljate.feature.complexity.Complexity;
import net.coljate.feature.complexity.TimeComplexity;

/**
 *
 * @author ollie
 */
public interface IterableExtension<T> extends Container, Iterable<T> {

    @TimeComplexity(Complexity.LINEAR)
    default int count() {
        return this.count(e -> true);
    }

    @TimeComplexity(Complexity.LINEAR)
    default int count(final Predicate<? super T> predicate) {
        int count = 0;
        for (final T element : this) {
            if (predicate.test(element)) {
                count = Math.addExact(count, 1);
            }
        }
        return count;
    }

    @TimeComplexity(Complexity.LINEAR)
    default long sum(final ToLongFunction<? super T> intFunction) {
        long sum = 0;
        for (final T element : this) {
            sum = Math.addExact(sum, intFunction.applyAsLong(element));
        }
        return sum;
    }

    @Override
    @TimeComplexity(Complexity.CONSTANT)
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @Override
    @TimeComplexity(Complexity.LINEAR)
    default boolean contains(final Object object) {
        for (final T element : this) {
            if (Objects.equals(object, element)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param predicate
     * @return true if any element in this container matches the given predicate.
     */
    @TimeComplexity(Complexity.LINEAR)
    default boolean anyMatch(final Predicate<? super T> predicate) {
        for (final T element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param predicate
     * @return true if no element matches the given predicate.
     */
    @TimeComplexity(Complexity.LINEAR)
    default boolean noneMatch(final Predicate<? super T> predicate) {
        return !this.anyMatch(predicate);
    }

    /**
     *
     * @param predicate
     * @param ifNone the result if this container is empty.
     * @return true if all elements match the given predicate, or if there are no elements.
     */
    @TimeComplexity(Complexity.LINEAR)
    default boolean allMatch(final Predicate<? super T> predicate, boolean ifNone) {
        final Iterator<T> iterator = this.iterator();
        if (!iterator.hasNext()) {
            return ifNone;
        }
        while (iterator.hasNext()) {
            if (!predicate.test(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    default T first(final Predicate<? super T> predicate) {
        for (final T element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

}
