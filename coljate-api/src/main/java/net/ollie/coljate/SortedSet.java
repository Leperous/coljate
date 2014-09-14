package net.ollie.coljate;

import net.ollie.coljate.imposed.sorting.Sorted;
import net.ollie.coljate.imposed.sorting.Sorter;
import net.ollie.coljate.utils.iterators.Iterables;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import java.util.Comparator;

/**
 * @author Ollie
 */
public interface SortedSet<V>
        extends Set<V>, Interval<V>, Sorted<V> {

    @Override
    default V first() {
        return this.lower().value();
    }

    default V last() {
        return this.upper().value();
    }

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

        @Override
        Immutable<V> and(V value);

        @Override
        Immutable<V> tail();

        @Override
        Immutable<V> not(Object object);

        @Nonnull
        @CheckReturnValue
        Immutable<V> sort(Sorter<? super V> comparator);

        @Override
        default SortedSet.Immutable<V> immutableCopy() {
            return this;
        }

    }

    interface Empty<V>
            extends SortedSet.Immutable<V>, Set.Empty<V> {

        @Override
        public default Bound<V> lower() {
            return Bound.of();
        }

        @Override
        public default Bound<V> upper() {
            return Bound.of();
        }

        @Override
        SortedSet.Singleton<V> and(V element);

        @Override
        default SortedSet.Empty<V> not(final Object element) {
            return this;
        }

        @Override
        default SortedSet.Empty<V> tail() {
            return this;
        }

        @Override
        default SortedSet.Empty<V> sort(final Sorter<? super V> comparator) {
            return this;
        }

        @Override
        default SortedSet.Empty<V> immutableCopy() {
            return this;
        }

    }

    interface Singleton<V>
            extends SortedSet.Immutable<V>, Set.Singleton<V> {

        @Override
        SortedSet.Empty<V> tail();

    }

}
