package net.ollie.sc4j;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.imposed.Mutability;
import net.ollie.sc4j.utils.Iterables;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @see java.util.Map
 * @see Cache
 */
public interface Map<K, V>
        extends Cached<K, V> {

    @Override
    Set<K> keys();

    Set<? extends Entry<? extends K, ? extends V>> entries();

    @Override
    Map<K, V> filterKeys(Predicate<? super K> predicate);

    @Override
    default Map<K, V> filter(Predicate<? super V> predicate) {
        return this.filterValues(predicate);
    }

    @Override
    Map<K, V> filterValues(Predicate<? super V> predicate);

    @Override
    Map.Mutable<K, V> mutable();

    @Override
    Map.Immutable<K, V> immutable();

    default boolean equals(final Map<?, ?> that) {
        return that != null
                && Objects.equals(this.entries(), that.entries());
    }

    @Override
    default int hash() {
        return this.entries().hashCode();
    }

    interface Entry<K, V> {

        @Nonnull
        K key();

        @CheckForNull
        V value();

        @Nonnull
        @CheckReturnValue
        Map.Mutable.Entry<K, V> mutable();

        @Nonnull
        @CheckReturnValue
        Map.Immutable.Entry<K, V> immutable();

        default boolean equals(final Map.Entry<?, ?> that) {
            return Objects.equals(this.key(), that.key())
                    && Objects.equals(this.value(), that.value());
        }

        default int hash() {
            return Objects.hashCode(this.key())
                    + Objects.hashCode(this.value());
        }

    }

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Mutable<K, V>
            extends Map<K, V>, Cached.Mutable<K, V> {

        @Override
        Set.Mutable<K> keys();

        @Override
        Set.Mutable<? extends Map.Mutable.Entry<K, V>> entries();

        @Override
        Map<K, V> putAll(Cached<K, V> map);

        interface Entry<K, V>
                extends Map.Entry<K, V>, Mutability.Mutable {

            void setValue(V value);

            @Override
            default void clear() {
                this.setValue(null);
            }

        }

    }

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Immutable<K, V>
            extends Map<K, V>, Cached.Immutable<K, V> {

        @Override
        Set.Immutable<K> keys();

        @Override
        Iteratable.Immutable<V> values();

        @Override
        Set.Immutable<? extends Map.Immutable.Entry<? extends K, ? extends V>> entries();

        @CheckReturnValue
        Map.Immutable<K, V> with(K key, V value);

        @CheckReturnValue
        Map.Immutable<K, V> without(Object key);

        @Override
        default Map.Immutable<K, V> immutable() {
            return this;
        }

        interface Entry<K, V>
                extends Map.Entry<K, V>, Mutability.Immutable {

            @Override
            default Map.Immutable.Entry<K, V> immutable() {
                return this;
            }

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
        Iteratable.Empty<V> values();

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
        default Map.Empty<K, V> filter(final Predicate<? super V> predicate) {
            return this;
        }

        @Override
        default Map.Empty<K, V> filterKeys(final Predicate<? super K> predicate) {
            return this;
        }

        @Override
        default Map.Empty<K, V> filterValues(Predicate<? super V> predicate) {
            return this;
        }

        @Override
        public default UnmodifiableIterator<V> iterator() {
            return Iterables.emptyIterator();
        }

        @Override
        public default Iteratable.Empty<V> tail() {
            return this.values().tail();
        }

    }

}
