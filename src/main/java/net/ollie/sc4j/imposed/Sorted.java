package net.ollie.sc4j.imposed;

import java.util.Comparator;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * Values are explicitly sorted according to some {@link Comparator}.
 *
 * @author Ollie
 * @see Ordered
 */
public interface Sorted<V>
        extends Ordered<V> {

    @Nonnull
    Comparator<? super V> comparator();

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

    /**
     *
     * @param <V>
     */
    interface Mutable<V>
            extends Sorted<V>, Ordered.Mutable<V> {

        /**
         * In-place sort.
         *
         * @param comparator
         */
        void sort(Comparator<? super V> comparator);

    }

    /**
     *
     * @param <V>
     */
    interface Immutable<V>
            extends Sorted<V>, Ordered.Immutable<V> {

        @CheckReturnValue
        @Nonnull
        Sorted.Immutable<V> sort(Comparator<? super V> comparator);

        @Override
        default Sorted.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
