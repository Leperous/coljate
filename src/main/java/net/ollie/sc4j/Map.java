package net.ollie.sc4j;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

import javax.annotation.CheckReturnValue;

/**
 *
 * @author Ollie
 * @see java.util.Map
 */
public interface Map<K, V>
        extends Cached<K, V> {

    @Override
    Map.Mutable<K, V> mutable();

    @Override
    Map.Immutable<K, V> immutable();

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Mutable<K, V>
            extends Map<K, V>, Cached.Mutable<K, V> {

        @Override
        Map<K, V> putAll(Cached<K, V> map);

    }

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Immutable<K, V>
            extends Map<K, V>, Cached.Immutable<K, V> {

        @CheckReturnValue
        Map.Immutable<K, V> with(K key, V value);

        @CheckReturnValue
        Map.Immutable<K, V> without(Object key);

        @Override
        default Map.Immutable<K, V> immutable() {
            return this;
        }

    }

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Empty<K, V>
            extends Iteratable.Empty<V>, Map.Immutable<K, V> {

        @Override
        default boolean isEmpty() {
            return Iteratable.Empty.super.isEmpty();
        }

        @Override
        default boolean contains(final Object object) {
            return Iteratable.Empty.super.contains(object);
        }

        @Override
        public abstract Iteratable.Empty<V> values();

        @Override
        default V get(final Object key) {
            return null;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Map.Empty<K, V2> map(Function<? super V, ? extends V2> function) {
            return (Map.Empty<K, V2>) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <K2> Map<K2, V> mapKeys(Function<? super K, ? extends K2> function) {
            return (Map.Empty<K2, V>) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        default <V2> Map<K, V2> mapValues(Function<? super V, ? extends V2> function) {
            return (Map.Empty<K, V2>) this;
        }

        @Override
        default Map<K, V> filterKeys(final Predicate<? super K> predicate) {
            return this;
        }

        @Override
        default Map<K, V> filterValues(Predicate<? super V> predicate) {
            return this;
        }

        @Override
        public default UnmodifiableIterator<V> iterator() {
            return Iterables.emptyIterator();
        }

    }

}
