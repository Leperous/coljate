package net.coljate.map;

import java.util.Spliterator;
import java.util.Spliterators;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.coljate.collection.ImmutableCollection;
import net.coljate.map.impl.EmptyMap;
import net.coljate.map.impl.ImmutableMapIterator;
import net.coljate.map.impl.ImmutableWrappedMap;
import net.coljate.set.ImmutableSet;
import net.coljate.util.iterator.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public interface ImmutableMap<K, V> extends Map<K, V>, ImmutableSet<Entry<K, V>> {

    @Override
    @CheckForNull
    ImmutableEntry<K, V> getEntry(@Nullable Object key);

    @Override
    ImmutableSet<K> keys();

    @Override
    ImmutableCollection<V> values();

    @Override
    default ImmutableMap<K, V> with(@Nonnull final Entry<K, V> entry) {
        return this.with(entry.key(), entry.value());
    }

    @Nonnull
    ImmutableMap<K, V> with(K key, V value);

    @Override
    @Deprecated
    default ImmutableMap<K, V> immutableCopy() {
        return this;
    }

    @Override
    default UnmodifiableIterator<Entry<K, V>> iterator() {
        return new ImmutableMapIterator<>(this.keys(), this::getEntry);
    }

    @Override
    default Spliterator<Entry<K, V>> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.SIZED | Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.IMMUTABLE);
    }

    static <K, V> ImmutableMap<K, V> of() {
        return EmptyMap.instance();
    }

    static <K, V> ImmutableMap<K, V> copyIntoHashMap(final Map<? extends K, ? extends V> map) {
        return ImmutableWrappedMap.copyIntoHashMap(map);
    }

}
