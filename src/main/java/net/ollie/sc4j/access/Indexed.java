package net.ollie.sc4j.access;

import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.SortedSet;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.NonNegativeInteger;

import javax.annotation.CheckForNull;

/**
 * Keyed by a number.
 *
 * @author Ollie
 */
public interface Indexed<V>
        extends Finite<V>, Keyed.Single<NonNegativeInteger, V> {

    V get(int index) throws IndexOutOfBoundsException;

    @Override
    default V get(final Object key) throws IndexOutOfBoundsException {
        return key instanceof Number
                ? this.get(((Number) key).intValue())
                : null;
    }

    Indexed<V> segment(int from, int to) throws IndexOutOfBoundsException;

    @Override
    SortedSet<NonNegativeInteger> keys();

    @Override
    Indexed<V> tail();

    @Override
    default Indexed<V> values() {
        return this;
    }

    @CheckForNull
    default NonNegativeInteger indexOf(final Object value) {
        final OptionalInt optional = Iterables.indexOf(this, value);
        return NonNegativeInteger.maybe(optional);
    }

    @Override
    default boolean contains(final Object object) {
        return this.indexOf(object) != null;
    }

    @Override
    <V2> Indexed<V2> map(Function<? super V, ? extends V2> function);

    @Override
    default <V2> Indexed<V2> mapValues(final Function<? super V, ? extends V2> function) {
        return this.map(function);
    }

    @Override
    Indexed<V> filter(Predicate<? super V> predicate);

    @Override
    Indexed<V> filterKeys(Predicate<? super NonNegativeInteger> predicate);

    @Override
    default Indexed<V> filterValues(final Predicate<? super V> predicate) {
        return this.filter(predicate);
    }

    @Override
    default boolean isEmpty() {
        return Finite.super.isEmpty();
    }

    @Override
    Indexed.Mutable<V> mutableCopy();

    @Override
    Indexed.Immutable<V> immutableCopy();

    /**
     *
     * @param <V>
     */
    interface Mutable<V>
            extends Indexed<V>, Finite.Mutable<V> {

        V set(int index, V value) throws IndexOutOfBoundsException;

        void insert(int index, V value) throws IndexOutOfBoundsException;

        default V remove(final int index) throws IndexOutOfBoundsException {
            return this.set(index, null);
        }

    }

    /**
     *
     * @param <V>
     */
    interface Immutable<V>
            extends Indexed<V>, Finite.Immutable<V> {

        @Override
        Indexed.Immutable<V> tail();

        @Override
        Indexed.Immutable<V> filter(Predicate<? super V> predicate);

        @Override
        default Indexed.Immutable<V> values() {
            return this;
        }

        @Override
        default Indexed.Immutable<V> immutableCopy() {
            return this;
        }

    }

}
