package net.ollie.sc4j;

import net.ollie.sc4j.imposed.Duplicated;

/**
 *
 * @author Ollie
 * @see Set
 */
public interface Multiset<V>
        extends Set<V>, Duplicated<V> {

    @Override
    Multiset.Mutable<V> mutable();

    @Override
    Multiset.Immutable<V> immutable();

    interface Mutable<V>
            extends Multiset<V>, Set.Mutable<V> {

        int increment(V value);

        int decrement(Object value);

        @Override
        default boolean add(final V value) {
            return this.increment(value) == 1;
        }

    }

    interface Immutable<V>
            extends Multiset<V>, Set.Immutable<V> {

        @Override
        default Multiset.Immutable<V> immutable() {
            return this;
        }

    }

}
