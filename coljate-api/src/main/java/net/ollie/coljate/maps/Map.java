package net.ollie.coljate.maps;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.coljate.Collection;
import net.ollie.coljate.sets.Set;
import net.ollie.coljate.theory.PartialFunction;

/**
 *
 * @author Ollie
 */
public interface Map<K, V> extends PartialFunction<K, V> {

    @CheckForNull
    V get(Object key);

    @Nonnull
    Set<K> keys();

    @Nonnull
    Set<? extends MapEntry<K, V>> entries();

    @Nonnull
    Collection<V> values();

    @Nonnull
    ImmutableMap<K, V> immutableCopy();

    @Nonnull
    MutableMap<K, V> mutableCopy();

    @Override
    default V apply(final K input) {
        return this.get(input);
    }

    default int size() {
        return this.keys().size();
    }

    default boolean isEmpty() {
        return this.keys().isEmpty();
    }

}
