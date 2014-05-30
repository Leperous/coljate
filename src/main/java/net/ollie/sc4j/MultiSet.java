package net.ollie.sc4j;

import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.imposed.Duplicated;
import net.ollie.sc4j.utils.NonNegative;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see Set
 */
public interface Multiset<V>
        extends Set<V>, Duplicated<V> {

    @Override
    Set<V> keys();

    @Override
    Iteratable<NonNegative> values();

    @Override
    Multiset<V> filter(Predicate<? super V> predicate);

    @Nonnull
    Map<V, NonNegative> map();

    @Override
    Multiset.Mutable<V> mutable();

    @Override
    Multiset.Immutable<V> immutable();

    default boolean equals(final Multiset<?> that) {
        return that != null
                && this.keys().equals(that.keys())
                && this.values().equals(that.values());
    }

    @Override
    default int hash() {
        return this.keys().hashCode() + this.values().hashCode();
    }

    interface Mutable<V>
            extends Multiset<V>, Set.Mutable<V> {

        /**
         * Increment the given value by 1. If the value is not present, it is
         * added and 1 is returned.
         *
         * TODO do we need to worry about heap pollution if untyped?
         *
         * @param value
         * @return
         */
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

        @Override
        Multiset.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        Map.Immutable<V, NonNegative> map();

    }

}
