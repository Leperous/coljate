package net.ollie.sc4j.imposed;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 * @param <K> key type
 * @param <V> value type
 */
public interface Cached<K, V>
        extends Keyed<K, V>, Iteratable<V> {

    default V getOrDefault(final Object key, V defaultValue) {
        final V value = this.get(key);
        return value == null
                ? defaultValue
                : value;
    }

    @Override
    Iteratable<V> values();

    @Override
    default Iteratable<V> tail() {
        return this.values().tail();
    }

    @Override
    default boolean contains(final Object object) {
        return this.values().contains(object);
    }

    @Override
    default Object[] toRawArray() {
        return this.values().toRawArray();
    }

    @Override
    Cached<K, V> filterKeys(Predicate<? super K> predicate);

    @Override
    Cached<K, V> filterValues(Predicate<? super V> predicate);

    @Override
    default Cached<K, V> filter(final Predicate<? super V> predicate) {
        return this.filterValues(predicate);
    }

    @Override
    <K2> Cached<K2, V> mapKeys(Function<? super K, ? extends K2> function);

    @Override
    <V2> Cached<K, V2> mapValues(Function<? super V, ? extends V2> function);

    @Override
    default <V2> Collection<V2> map(final Function<? super V, ? extends V2> function) {
        return this.mapValues(function);
    }

    @Override
    default Iterator<V> iterator() {
        return this.values().iterator();
    }

    @Override
    Cached.Mutable<K, V> mutable();

    @Override
    Cached.Immutable<K, V> immutable();

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Mutable<K, V>
            extends Cached<K, V>, Iteratable.Mutable<V> {

        default boolean add(final K key, final V value) {
            return this.put(key, value) == null;
        }

        default boolean addAll(final Cached<K, V> keyed) {
            return !this.putAll(keyed).isEmpty();
        }

        @CheckForNull
        V put(K key, V value);

        @Nonnull
        Cached<K, V> putAll(Cached<K, V> keyed);

        @CheckForNull
        V putIfAbsent(K key, V value);

        @CheckForNull
        default V putIfAbsent(final K key, final Supplier<? extends V> supplier) {
            final V value = this.get(key);
            return value == null
                    ? this.putIfAbsent(key, supplier.get())
                    : value;
        }

        @CheckForNull
        V remove(Object key);

        boolean replace(K key, V oldValue, V newValue);

    }

    interface Immutable<K, V>
            extends Cached<K, V>, Iteratable.Immutable<V> {

        @Override
        default Cached.Immutable<K, V> immutable() {
            return this;
        }

        @Override
        Iteratable.Immutable<V> values();

        @Override
        default UnmodifiableIterator<V> iterator() {
            return this.values().iterator();
        }

    }

}
