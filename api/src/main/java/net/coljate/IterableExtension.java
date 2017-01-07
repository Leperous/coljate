package net.coljate;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.coljate.util.complexity.Complexity;
import net.coljate.util.complexity.TimeComplexity;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface IterableExtension<T> extends Container, Iterable<T> {

    @TimeComplexity(Complexity.LINEAR)
    default int count() {
        return this.count(e -> true);
    }

    @TimeComplexity(Complexity.LINEAR)
    default int count(@Nonnull final Predicate<? super T> predicate) {
        int count = 0;
        for (final T element : this) {
            if (predicate.test(element)) {
                count = Math.addExact(count, 1);
            }
        }
        return count;
    }

    @TimeComplexity(Complexity.LINEAR)
    default long sum(@Nonnull final ToLongFunction<? super T> intFunction) {
        long sum = 0;
        for (final T element : this) {
            sum = Math.addExact(sum, intFunction.applyAsLong(element));
        }
        return sum;
    }

    /**
     * @return true if this iterable container is currently empty.
     */
    @Override
    @TimeComplexity(Complexity.CONSTANT)
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @Override
    @TimeComplexity(Complexity.LINEAR)
    default boolean contains(@Nullable final Object object) {
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
    default boolean anyMatch(@Nonnull final Predicate<? super T> predicate) {
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
    default boolean noneMatch(@Nonnull final Predicate<? super T> predicate) {
        return !this.anyMatch(predicate);
    }

    /**
     *
     * @param predicate
     * @param ifNone the result if this container is empty.
     * @return true if all elements match the given predicate, or the given boolean if this is empty.
     */
    @TimeComplexity(Complexity.LINEAR)
    default boolean allMatch(@Nonnull final Predicate<? super T> predicate, final boolean ifNone) {
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

    @CheckForNull
    default T first(@Nonnull final Predicate<? super T> predicate) {
        for (final T element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    @CheckForNull
    default T last(@Nonnull final Predicate<? super T> predicate) {
        T last = null;
        for (final T element : this) {
            if (predicate.test(element)) {
                last = element;
            }
        }
        return last;
    }

}