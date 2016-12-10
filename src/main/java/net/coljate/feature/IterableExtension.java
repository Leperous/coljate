package net.coljate.feature;

import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import net.coljate.Container;

/**
 *
 * @author ollie
 */
public interface IterableExtension<T> extends Container, Iterable<T> {

    default int count() {
        return this.count(e -> true);
    }

    default int count(final Predicate<? super T> predicate) {
        int count = 0;
        for (final T element : this) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }

    default int sum(final ToIntFunction<? super T> intFunction) {
        int sum = 0;
        for (final T element : this) {
            sum += intFunction.applyAsInt(element);
        }
        return sum;
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @Override
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
     * @return true if any elemenet matches the given predicate.
     */
    default boolean anyMatch(final Predicate<? super T> predicate) {
        for (final T element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    default boolean noneMatch(final Predicate<? super T> predicate) {
        return !this.anyMatch(predicate);
    }

    /**
     *
     * @param predicate
     * @return true if all elements match the given predicate, or if there are no elements.
     */
    default boolean allMatch(final Predicate<? super T> predicate) {
        for (final T element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

}
