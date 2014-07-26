package net.ollie.sc4j;

import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.imposed.Duplicated;
import net.ollie.sc4j.utils.NonNegativeInteger;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see Set
 */
public interface MultiSet<V>
        extends Set<V>, Duplicated<V> {

    @Override
    Set<V> keys();

    @Override
    Iteratable<NonNegativeInteger> values();

    @Override
    MultiSet<V> filter(Predicate<? super V> predicate);

    @Nonnull
    Map<V, NonNegativeInteger> map();

    @Override
    default boolean isEmpty() {
        return Duplicated.super.isEmpty();
    }

    @Override
    MultiSet.Mutable<V> mutableCopy();

    @Override
    MultiSet.Immutable<V> immutableCopy();

    default boolean equals(final MultiSet<?> that) {
        return that != null
                && this.keys().equals(that.keys())
                && this.values().equals(that.values());
    }

    @Override
    default int hash() {
        return this.keys().hashCode() + this.values().hashCode();
    }

    interface Mutable<V>
            extends MultiSet<V>, Set.Mutable<V>, Duplicated.Mutable<V> {

        /**
         * Increment the given value by 1. If the value is not present, it is added and 1 is returned.
         *
         * TODO do we need to worry about heap pollution if untyped?
         *
         * @param value
         * @return
         */
        int increment(V value);

        int decrement(V value);

        @Override
        default boolean add(final V value) {
            return this.increment(value) == 1;
        }

    }

    interface Immutable<V>
            extends MultiSet<V>, Set.Immutable<V> {

        @Override
        default MultiSet.Immutable<V> immutableCopy() {
            return this;
        }

        @Override
        MultiSet.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        Map.Immutable<V, NonNegativeInteger> map();

    }

}
