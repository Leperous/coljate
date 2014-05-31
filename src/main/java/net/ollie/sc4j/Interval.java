package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Sorted;
import net.ollie.sc4j.imposed.Unique;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A sorted collection of unique elements.
 *
 * For example, all doubles in the set {@code [0, 1)}, or all dates in the 20th century.
 *
 * @author Ollie
 */
public interface Interval<V>
        extends Sorted<V>, Unique<V> {

    @Override
    Interval.Mutable<V> mutable();

    @Override
    Interval.Immutable<V> immutable();

    interface Mutable<V>
            extends Interval<V>, Sorted.Mutable<V> {

        void setFirst(V value);

        void setLast(V value);

    }

    interface Immutable<V>
            extends Interval<V>, Sorted.Immutable<V> {

        @CheckReturnValue
        @Nonnull
        Interval.Immutable<V> withFirst(V min);

        @CheckReturnValue
        @Nonnull
        Interval.Immutable<V> withLast(V max);

        @Override
        default Interval.Immutable<V> immutable() {
            return this;
        }

    }

}
