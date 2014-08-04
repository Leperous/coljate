package net.ollie.sc4j.access;

import net.ollie.sc4j.SortedSet;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.numeric.NonNegativeInt;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Keyed by a number.
 *
 * @author Ollie
 */
public interface Indexed<N extends Number, V>
        extends Finite<V>, Keyed.Single<N, V> {

    V get(@Nonnull N index) throws IndexOutOfBoundsException;

    @Nonnull
    @CheckReturnValue
    Indexed<N, V> segment(N from, N to) throws IndexOutOfBoundsException;

    @Override
    SortedSet<N> keys();

    @Override
    Indexed<N, V> tail();

    @Override
    default Indexed<N, V> values() {
        return this;
    }

    @CheckForNull
    default NonNegativeInteger indexOf(final Object value) {
        final OptionalInt optional = Iterables.indexOf(this, value);
        return NonNegativeInt.maybe(optional);
    }

    @Override
    default boolean contains(final Object object) {
        return this.indexOf(object) != null;
    }

    @Override
    <V2> Indexed<N, V2> map(Function<? super V, ? extends V2> function);

    @Override
    default <V2> Indexed<N, V2> mapValues(final Function<? super V, ? extends V2> function) {
        return this.map(function);
    }

    @Override
    Indexed<N, V> filter(Predicate<? super V> predicate);

    @Override
    Indexed<N, V> filterKeys(Predicate<? super N> predicate);

    @Override
    default Indexed<N, V> filterValues(final Predicate<? super V> predicate) {
        return this.filter(predicate);
    }

    @Override
    default boolean isEmpty() {
        return Finite.super.isEmpty();
    }

    @Override
    Indexed.Mutable<N, V> mutableCopy();

    @Override
    Indexed.Immutable<N, V> immutableCopy();

    /**
     * @param <V>
     */
    interface Mutable<N extends Number, V>
            extends Indexed<N, V>, Finite.Mutable<V> {

        V set(N index, V value) throws IndexOutOfBoundsException;

        void insert(N index, V value) throws IndexOutOfBoundsException;

        default V remove(final N index) throws IndexOutOfBoundsException {
            return this.set(index, null);
        }

    }

    /**
     * @param <V>
     */
    interface Immutable<N extends Number, V>
            extends Indexed<N, V>, Finite.Immutable<V> {

        @Override
        Indexed.Immutable<N, V> tail();

        @Override
        Indexed.Immutable<N, V> filter(Predicate<? super V> predicate);

        @Override
        <V2> Indexed.Immutable<N, V2> map(Function<? super V, ? extends V2> function);

        @Override
        default Indexed.Immutable<N, V> values() {
            return this;
        }

        @Override
        default Indexed.Immutable<N, V> immutableCopy() {
            return this;
        }

    }

    interface Empty<N extends Number, V>
            extends Indexed.Immutable<N, V>, Finite.Empty<V> {

        @Override
        default boolean isEmpty() {
            return Finite.Empty.super.isEmpty();
        }

        @Override
        default boolean contains(final Object object) {
            return Finite.Empty.super.contains(object);
        }

        @Override
        default Indexed.Empty<N, V> tail() {
            return this;
        }

        @Override
        default Indexed.Empty<N, V> filter(Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default <V2> Indexed.Empty<N, V2> map(Function<? super V, ? extends V2> function) {
            return (Indexed.Empty<N, V2>) this;
        }

    }

}
