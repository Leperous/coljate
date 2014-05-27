package net.ollie.sc4j.access;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.imposed.Unique;

/**
 * A collection of objects that are accessed through providing some kind of key.
 *
 * @author Ollie
 * @param <K> key type
 * @param <V> value type
 */
public interface Keyed<K, V> {

    @CheckForNull
    V get(@Nonnull K key);

    @Nonnull
    Unique<K> keys();

    default boolean containsKey(final K key) {
        return this.keys().contains(key);
    }

    Collection<V> values();

    default boolean containsValue(final V value) {
        return this.values().contains(value);
    }

    @CheckReturnValue
    @Nonnull
    <K2> Keyed<K2, V> mapKeys(Function<? super K, ? extends K2> function);

    @CheckReturnValue
    @Nonnull
    <V2> Keyed<K, V2> mapValues(Function<? super V, ? extends V2> function);

    @CheckReturnValue
    @Nonnull
    Keyed<K, V> filterKeys(Predicate<? super K> predicate);

    @CheckReturnValue
    @Nonnull
    Keyed<K, V> filterValues(Predicate<? super V> predicate);

    interface BiKeyed<K1, K2, V>
            extends Keyed<Map.Entry<K1, K2>, V> {

        V get(K1 key1, K2 key2);

        @Override
        default V get(Map.Entry<K1, K2> entry) {
            return this.get(entry.getKey(), entry.getValue());
        }

    }

}
