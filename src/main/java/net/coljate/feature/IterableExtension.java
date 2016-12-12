package net.coljate.feature;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import net.coljate.Container;

/**
 *
 * @author ollie
 */
public interface IterableExtension<T> extends Container, Iterable<T> {

    @Complexity(Complexity.Order.LINEAR)
    default int count() {
        return this.count(e -> true);
    }

    @Complexity(Complexity.Order.LINEAR)
    default int count(final Predicate<? super T> predicate) {
        int count = 0;
        for (final T element : this) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }

    @Complexity(Complexity.Order.LINEAR)
    default int sum(final ToIntFunction<? super T> intFunction) {
        int sum = 0;
        for (final T element : this) {
            sum += intFunction.applyAsInt(element);
        }
        return sum;
    }

    @Override
    @Complexity(Complexity.Order.CONSTANT)
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @Override
    @Complexity(Complexity.Order.LINEAR)
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
    @Complexity(Complexity.Order.LINEAR)
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
    @Complexity(Complexity.Order.LINEAR)
    default boolean noneMatch(final Predicate<? super T> predicate) {
        return !this.anyMatch(predicate);
    }

    /**
     *
     * @param predicate
     * @param ifNone the result if this container is empty.
     * @return true if all elements match the given predicate, or if there are no elements.
     */
    @Complexity(Complexity.Order.LINEAR)
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

}
