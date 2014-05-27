package net.ollie.sc4j;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.sc4j.imposed.Sorted;
import net.ollie.sc4j.imposed.Unique;

/**
 * A sorted collection of unique elements.
 *
 * For example, all doubles in the set {@code [0, 1)}, or all dates in the 20th
 * century.
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
            extends Interval<V>, Sorted.Mutable<V>, Unique.Mutable<V> {

        V setMaximum(V value);

        V setMinimum(V value);

    }

    interface Immutable<V>
            extends Interval<V>, Sorted.Immutable<V>, Unique.Immutable<V> {

        @Override
        default Interval.Immutable<V> immutable() {
            return this;
        }

        @CheckReturnValue
        @Nonnull
        Interval.Immutable<V> withMaximum(V value);

        @CheckReturnValue
        @Nonnull
        Interval.Immutable<V> withMinimum(V value);

    }

}
