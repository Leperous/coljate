package net.ollie.sc4j.imposed;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Traversable;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * Ordered and iterable.
 *
 * @author Ollie
 * @see Ordered
 */
public interface Sorted<V>
        extends Traversable<V> {

    @Nonnull
    Comparator<? super V> comparator();

    @CheckForNull
    V first();

    @CheckForNull
    V last();

    @Override
    default V head() {
        return this.first();
    }

    @Nonnull
    default V last(final Predicate<? super V> predicate) throws NoSuchElementException {
        final V last = this.lastOrElse(predicate, null);
        if (last == null) {
            throw new NoSuchElementException();
        } else {
            return last;
        }
    }

    V lastOrElse(Predicate<? super V> predicate, V defaultValue);

    /**
     * @return a mutable copy of this collection.
     */
    @Override
    Sorted.Mutable<V> mutableCopy();

    /**
     * @return an immutable copy of this collection.
     */
    @Override
    Sorted.Immutable<V> immutableCopy();

    default boolean equals(final Sorted<?> that) {
        return that != null && this.isEmpty()
                ? that.isEmpty()
                : Objects.equals(this.head(), that.head());
    }

    default int hash() {
        return this.isEmpty()
                ? 1
                : Objects.hashCode(this.head()) * Objects.hashCode(this.tail());
    }

    /**
     *
     * @param <V>
     */
    interface Mutable<V>
            extends Sorted<V>, Traversable.Mutable<V> {

        void sort(Comparator<? super V> comparator);

    }

    /**
     *
     * @param <V>
     */
    interface Immutable<V>
            extends Sorted<V>, Traversable.Immutable<V> {

        @CheckReturnValue
        @Nonnull
        Sorted.Immutable<V> sort(Comparator<? super V> comparator);

        @Override
        default Sorted.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
