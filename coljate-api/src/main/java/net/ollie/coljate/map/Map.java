package net.ollie.coljate.map;

import java.util.Objects;
import java.util.function.BiConsumer;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.Collection;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.theory.Associative;
import net.ollie.coljate.theory.Finite;

/**
 *
 * @author Ollie
 */
public interface Map<K, @Nullable V>
        extends Collection<MapEntry<K, V>>, Associative<K, V> {

    @Override
    V get(@Nullable Object key);

    @NonNull
    Set<K> keys();

    @NonNull
    Collection<V> values();

    @Override
    ImmutableMap<K, V> immutableCopy();

    @Override
    MutableMap<K, V> mutableCopy();

    @Override
    Map<K, V> tail();

    @Override
    default V apply(final K input) {
        return this.get(input);
    }

    @Override
    default int count() {
        return this.keys().count();
    }

    default boolean containsKey(final Object key) {
        return this.keys().contains(key);
    }

    default boolean containsValue(final Object value) {
        return this.values().contains(value);
    }

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof MapEntry
                && this.contains((MapEntry) object);
    }

    default boolean contains(@NonNull final MapEntry<?, ?> entry) {
        return entry != null && Objects.equals(entry.value(), this.get(entry.key()));
    }

    @Override
    default boolean inDomain(final K input) {
        return this.keys().contains(input);
    }

    default void forEach(final BiConsumer<? super K, ? super V> consumer) {
        this.iterator().forEachRemaining(entry -> consumer.accept(entry.key(), entry.value()));
    }

    static boolean equal(final Map<?, ?> m1, final Map<?, ?> m2) {
        return Finite.elementsEqual(m1, m2);
    }

    static int hash(final Map<?, ?> map) {
        return Finite.sumHash(map);
    }

    static String toString(final Map<?, ?> map) {
        return Finite.toString(map, entry -> entry.key() + "=" + entry.value());
    }

}
