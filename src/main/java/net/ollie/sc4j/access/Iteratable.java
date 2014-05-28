package net.ollie.sc4j.access;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import net.ollie.sc4j.utils.Iterables;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Iteratable<V>
        extends Traversable<V>, Iterable<V> {

    default int size() {
        return Iterables.doCount(this);
    }

    @Override
    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    @CheckReturnValue
    default <R> R reduce(final BiFunction<R, V, ? extends R> function, final R initial) {
        R current = initial;
        for (final V element : this) {
            current = function.apply(current, element);
        }
        return current;
    }

    @Nonnull
    Object[] toRawArray();

    @Override
    default boolean contains(final Object object) {
        for (final V element : this) {
            if (Objects.equals(element, object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    default V head() {
        final Iterator<V> iterator = this.iterator();
        return iterator.hasNext()
                ? iterator.next()
                : null;
    }

    @Override
    default V findOrElse(final Predicate<? super V> predicate, final V defaultValue) {
        for (final V element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return defaultValue;
    }

    default boolean equals(final Iteratable<?> that) {
        return that != null
                && Iterables.equals(this, that);
    }

    interface Empty<V>
            extends net.ollie.sc4j.imposed.Empty<V>, Iteratable<V> {

        @Override
        default boolean isEmpty() {
            return net.ollie.sc4j.imposed.Empty.super.isEmpty();
        }

        @Override
        default int size() {
            return 0;
        }

        @Override
        default boolean contains(final Object object) {
            return net.ollie.sc4j.imposed.Empty.super.contains(object);
        }

        @Override
        default V head() {
            return null;
        }

        @Override
        default Empty<V> tail() {
            return this;
        }

        @Override
        default Iterator<V> iterator() {
            return Collections.emptyIterator();
        }

        @Override
        default <R> R reduce(final BiFunction<R, V, ? extends R> function, final R initial) {
            return initial;
        }

        @Override
        default V findOrElse(Predicate<? super V> predicate, V defaultValue) {
            return net.ollie.sc4j.imposed.Empty.super.findOrElse(predicate, defaultValue);
        }

    }

}
