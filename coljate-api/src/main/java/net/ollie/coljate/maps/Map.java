package net.ollie.coljate.maps;

import java.util.Objects;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.Collection;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.theory.PartialFunction;

/**
 *
 * @author Ollie
 */
public interface Map<K, V> extends Collection<MapEntry<K, V>>, PartialFunction<K, V> {

    @CheckForNull
    V get(Object key);

    @Nonnull
    Set<K> keys();

    @Nonnull
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
    default int size() {
        return this.keys().size();
    }

    @Override
    default boolean contains(final Object object) {
        return object instanceof MapEntry
                && this.contains((MapEntry) object);
    }

    default boolean contains(@NonNull final MapEntry<?, ?> entry) {
        return Objects.equals(entry.value(), this.get(entry.key()));
    }

}
