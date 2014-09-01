package net.ollie.sc4j.imposed;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

import net.ollie.sc4j.access.Streamable;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.iterators.UnmodifiableIterator;

import javax.annotation.CheckForNull;

/**
 * Values are stored.
 *
 * @author Ollie
 * @param <K> key type
 * @param <V> value type
 */
public interface Cached<K, V>
        extends Keyed.Single<K, V>, Streamable<V> {

    default V getOrDefault(final Object key, final V defaultValue) {
        return this.getOrElse(key, () -> defaultValue);
    }

    default V getOrElse(final Object key, final Supplier<? extends V> supplier) {
        final V value = this.get(key);
        return value == null
                ? supplier.get()
                : value;
    }

    @Override
    Streamable<V> values();

    @Override
    default Streamable<V> tail() {
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
    default Iterator<V> iterator() {
        return this.values().iterator();
    }

    @Override
    default boolean isEmpty() {
        return Keyed.Single.super.isEmpty();
    }

    @Override
    Cached.Mutable<K, V> mutableCopy();

    @Override
    Cached.Immutable<K, V> immutableCopy();

    /**
     *
     * @param <K>
     * @param <V>
     */
    @javax.annotation.concurrent.NotThreadSafe
    interface Mutable<K, V>
            extends Cached<K, V>, Streamable.Mutable<V> {

        @CheckForNull
        V put(K key, V value);

        @CheckForNull
        default V putIfAbsent(final K key, final V value) {
            V current = this.get(key);
            if (current == null) {
                current = this.put(key, value);
            }
            return current;
        }

        @CheckForNull
        default V putIfAbsent(final K key, final Supplier<? extends V> supplier) {
            V value = this.get(key);
            if (value == null) {
                value = this.putIfAbsent(key, supplier.get());
            }
            return value;
        }

        @CheckForNull
        default V ensure(final K key, final Supplier<? extends V> supplier) {
            final V value = this.putIfAbsent(key, supplier);
            return value == null
                    ? this.get(key)
                    : value;
        }

        @CheckForNull
        V remove(Object key);

        default boolean replace(final K key, final V expectedValue, final V newValue) {
            final V currentValue = this.get(expectedValue);
            if (Objects.equals(expectedValue, currentValue)) {
                this.put(key, newValue);
                return true;
            } else {
                return false;
            }
        }

    }

    @javax.annotation.concurrent.Immutable
    interface Immutable<K, V>
            extends Cached<K, V>, Streamable.Immutable<V> {

        @Override
        default Streamable.Immutable<V> tail() {
            return Cached.super.tail().immutableCopy();
        }

        @Override
        default Cached.Immutable<K, V> immutableCopy() {
            return this;
        }

        @Override
        Streamable.Immutable<V> values();

        @Override
        default UnmodifiableIterator<V> iterator() {
            return this.values().iterator();
        }

    }

}
