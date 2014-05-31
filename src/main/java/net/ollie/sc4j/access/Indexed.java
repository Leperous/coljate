package net.ollie.sc4j.access;

import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.SortedSet;
import net.ollie.sc4j.utils.Iterables;

/**
 * Keyed by a number.
 *
 * @author Ollie
 */
public interface Indexed<V>
        extends Iteratable<V>, Keyed<Integer, V> {

    V get(int index) throws IndexOutOfBoundsException;

    @Override
    default V get(final Object key) throws IndexOutOfBoundsException {
        return key instanceof Number
                ? this.get(((Number) key).intValue())
                : null;
    }

    Indexed<V> segment(int from, int to) throws IndexOutOfBoundsException;

    @Override
    SortedSet<Integer> keys();

    @Override
    Indexed<V> tail();

    @Override
    default Indexed<V> values() {
        return this;
    }

    default OptionalInt indexOf(final Object value) {
        return Iterables.indexOf(this, value);
    }

    @Override
    default boolean contains(final Object object) {
        return this.indexOf(object).isPresent();
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
    Indexed<V> filterKeys(Predicate<? super Integer> predicate);

    @Override
    default Indexed<V> filterValues(final Predicate<? super V> predicate) {
        return this.filter(predicate);
    }

    @Override
    Indexed.Mutable<V> mutable();

    @Override
    Indexed.Immutable<V> immutable();

    /**
     *
     * @param <V>
     */
    interface Mutable<V>
            extends Indexed<V>, Iteratable.Mutable<V> {

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
            extends Indexed<V>, Iteratable.Immutable<V> {

        @Override
        default Indexed.Immutable<V> values() {
            return this;
        }

        @Override
        default Indexed.Immutable<V> immutable() {
            return this;
        }

    }

}
