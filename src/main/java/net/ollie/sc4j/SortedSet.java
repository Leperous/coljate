package net.ollie.sc4j;

import java.util.Comparator;
import java.util.function.Predicate;

import net.ollie.sc4j.imposed.Sorted;
import net.ollie.sc4j.utils.Iterables;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface SortedSet<V>
        extends Set<V>, Sorted<V> {

    @CheckForNull
    V last();

    @Override
    SortedSet<V> filter(Predicate<? super V> predicate);

    @Override
    SortedSet.Mutable<V> mutableCopy();

    @Override
    SortedSet.Immutable<V> immutableCopy();

    default boolean equals(final SortedSet<?> that) {
        return that != null
                && this.count() == that.count()
                && Iterables.equals(this, that);
    }

    @Override
    default int hash() {
        return Iterables.productHashCode(this);
    }

    interface Mutable<V>
            extends SortedSet<V>, Set.Mutable<V> {

        void sort(Comparator<? super V> comparator);

    }

    interface Immutable<V>
            extends SortedSet<V>, Set.Immutable<V> {

        @Nonnull
        @CheckReturnValue
        SortedSet.Immutable<V> sort(Comparator<? super V> comparator);

        @Override
        SortedSet.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        default SortedSet.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
