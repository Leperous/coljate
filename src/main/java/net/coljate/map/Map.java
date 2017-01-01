package net.coljate.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.coljate.collection.Collection;
import net.coljate.map.impl.ImmutableWrappedMap;
import net.coljate.map.impl.KeySortedMap;
import net.coljate.map.impl.MapIterator;
import net.coljate.map.impl.MutableWrappedHashMap;
import net.coljate.map.impl.RepeatedValueMap;
import net.coljate.map.impl.SingletonMap;
import net.coljate.map.lazy.LazyFilteredMap;
import net.coljate.map.lazy.LazyMap;
import net.coljate.set.Set;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 * @since 1.0
 */
public interface Map<K, V> extends Set<Entry<K, V>>, Associative<K, V> {

    /**
     * @param key
     * @return the entry associated with this key, or null if there is no such association.
     */
    @CheckForNull
    Entry<K, V> getEntry(@Nullable Object key);

    /**
     * @return a view of the keys in this map.
     */
    @Nonnull
    Set<K> keys();

    /**
     * @return a view of the values in this map.
     */
    @Nonnull
    Collection<V> values();

    @Override
    default V getIfPresent(final Object key) {
        return Functions.ifNonNull(this.getEntry(key), Entry::value);
    }

    default V getOrDefault(final Object key, final Supplier<? extends V> supplier) {
        final Entry<K, V> got = this.getEntry(key);
        return got == null ? supplier.get() : got.value();
    }

    default V getOrDefault(final Object key, final V defaultValue) {
        final Entry<K, V> got = this.getEntry(key);
        return got == null ? defaultValue : got.value();
    }

    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof Entry
                && this.containsEntry((Entry) object);
    }

    default boolean contains(final Object key, final Object value) {
        final Entry<K, V> current = this.getEntry(key);
        return current != null
                && Objects.equals(current.key(), key)
                && Objects.equals(current.value(), value);
    }

    default boolean containsEntry(final Entry<?, ?> entry) {
        return this.contains(entry.key(), entry.value());
    }

    default boolean containsKey(@Nullable final Object key) {
        return this.keys().contains(key);
    }

    default boolean containsValue(@Nullable final Object value) {
        return this.values().contains(value);
    }

    default void forEach(final BiConsumer<? super K, ? super V> consumer) {
        this.forEach(entry -> consumer.accept(entry.key(), entry.value()));
    }

    default java.util.Map<K, V> mutableJavaMapCopy() {
        return this.javaMapCopy(java.util.HashMap::new);
    }

    default <M extends java.util.Map<K, V>> M javaMapCopy(final IntFunction<? extends M> mapSupplier) {
        final M map = mapSupplier.apply(this.count());
        this.forEach(map::put);
        return map;
    }

    @Override
    default Map<K, V> filter(final Predicate<? super Entry<K, V>> predicate) {
        return LazyFilteredMap.filterEntries(this, predicate);
    }

    default <V2> Map<K, V2> transformValues(final Function<? super V, ? extends V2> transform) {
        return LazyMap.transformValues(this, transform);
    }

    default SortedMap<K, V> sortKeys(final Comparator<? super K> comparator) {
        return KeySortedMap.copyOf(this, comparator);
    }

    @Override
    default MutableMap<K, V> mutableCopy() {
        return MutableMap.viewOf(this.mutableJavaMapCopy());
    }

    @Override
    default ImmutableMap<K, V> immutableCopy() {
        return ImmutableWrappedMap.copyIntoHashMap(this);
    }

    @Override
    default Iterator<Entry<K, V>> iterator() {
        return new MapIterator<>(this.keys(), this::getEntry);
    }

    @Override
    default Spliterator<Entry<K, V>> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.SIZED | Spliterator.DISTINCT | Spliterator.NONNULL);
    }

    static <K, V> MutableMap<K, V> create(final int initialCapacity) {
        return MutableWrappedHashMap.create(initialCapacity);
    }

    @SuppressWarnings("unchecked")
    static <K, V> Map<K, V> copyOrCast(final Collection<? extends Entry<? extends K, ? extends V>> collection) {
        return collection instanceof Map
                ? (Map<K, V>) collection
                : copyOf(collection);
    }

    static <K, V> Map<K, V> copyOf(final Collection<? extends Entry<? extends K, ? extends V>> entries) {
        return ImmutableWrappedMap.copyIntoHashMap(entries);
    }

    static <K, V> ImmutableMap<K, V> of() {
        return ImmutableMap.of();
    }

    static <K, V> ImmutableMap<K, V> of(final K key, final V value) {
        return SingletonMap.of(key, value);
    }

    static <K, V> Map<K, V> repeat(final Set<K> keys, final V value) {
        return RepeatedValueMap.viewOf(keys, value);
    }

}
