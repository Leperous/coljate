package net.ollie.sc4j.access;

import net.ollie.sc4j.SortedSet;
import net.ollie.sc4j.utils.iterators.Iterables;
import net.ollie.sc4j.utils.numeric.NonNegativeInteger;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import java.util.OptionalInt;

/**
 * An element {@code V} may be found given some integer index.
 *
 * @author Ollie
 */
public interface Indexed<N extends Number, V>
        extends Streamable<V>, Keyed.Single<N, V> {

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
        return NonNegativeInteger.maybe(optional);
    }

    @Override
    default boolean contains(final Object object) {
        return this.indexOf(object) != null;
    }

    @Override
    default boolean isEmpty() {
        return Streamable.super.isEmpty();
    }

    @Override
    Indexed.Mutable<N, V> mutableCopy();

    @Override
    Indexed.Immutable<N, V> immutableCopy();

    /**
     * @param <V>
     */
    interface Mutable<N extends Number, V>
            extends Indexed<N, V>, Streamable.Mutable<V> {

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
            extends Indexed<N, V>, Streamable.Immutable<V> {

        @Override
        Indexed.Immutable<N, V> tail();

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
            extends Indexed.Immutable<N, V>, Streamable.Empty<V> {

        @Override
        default boolean isEmpty() {
            return Streamable.Empty.super.isEmpty();
        }

        @Override
        default boolean contains(final Object object) {
            return Streamable.Empty.super.contains(object);
        }

        @Override
        default Indexed.Empty<N, V> tail() {
            return this;
        }

    }

}
