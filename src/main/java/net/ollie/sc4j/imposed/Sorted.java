package net.ollie.sc4j.imposed;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import net.ollie.sc4j.access.Traversable;

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

    default V last(Predicate<? super V> predicate) throws NoSuchElementException {
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
    Sorted.Mutable<V> mutable();

    /**
     * @return an immutable copy of this collection.
     */
    @Override
    Sorted.Immutable<V> immutable();

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
        default Sorted.Immutable<V> immutable() {
            return this;
        }

    }

}
