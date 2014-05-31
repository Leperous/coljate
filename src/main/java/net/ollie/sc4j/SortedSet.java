package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Sorted;

/**
 *
 * @author Ollie
 */
public interface SortedSet<V>
        extends Set<V>, Sorted<V> {

    @Override
    default V head() {
        return Sorted.super.head();
    }

    @Override
    SortedSet.Mutable<V> mutableCopy();

    @Override
    SortedSet.Immutable<V> immutableCopy();

    interface Mutable<V>
            extends SortedSet<V>, Set.Mutable<V>, Sorted.Mutable<V> {

    }

    interface Immutable<V>
            extends SortedSet<V>, Set.Immutable<V>, Sorted.Immutable<V> {

        @Override
        default SortedSet.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
