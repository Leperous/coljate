package net.coljate.map;

import java.util.Objects;
import java.util.function.BiConsumer;

import net.coljate.Collection;
import net.coljate.feature.Associative;
import net.coljate.set.Set;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface Map<K, V> extends Collection<Entry<K, V>>, Associative<K, V> {

    Entry<K, V> entry(Object key);

    @Override
    default V get(final Object key) {
        return Functions.ifNonNull(this.entry(key), Entry::value);
    }
    
    Set<K> keys();

    Collection<V> values();

    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof Entry
                && this.contains((Entry) object);
    }

    default boolean contains(final Entry<?, ?> entry) {
        final Entry<K, V> got = this.entry(entry.key());
        return Objects.equals(got, entry);
    }

    default boolean containsKey(final Object key) {
        return this.keys().contains(key);
    }

    default boolean containsValue(final Object value) {
        return this.values().contains(value);
    }

    default void forEach(final BiConsumer<? super K, ? super V> consumer) {
        this.forEach(entry -> consumer.accept(entry.key(), entry.value()));
    }

    @Override
    MutableMap<K, V> mutableCopy();

    @Override
    ImmutableMap<K, V> immutableCopy();


}
