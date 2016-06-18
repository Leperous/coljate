package net.ollie.coljate.map;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.Collection;
import net.ollie.coljate.set.Set;
import net.ollie.coljate.theory.Associative;

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

    @Override
    @Deprecated
    default boolean contains(final Object object) {
        return object instanceof MapEntry
                && this.contains((MapEntry) object);
    }

    default boolean contains(@NonNull final MapEntry<?, ?> entry) {
        return Objects.equals(entry.value(), this.get(entry.key()));
    }

}
