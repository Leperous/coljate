package net.ollie.sc4j.access;

import java.util.function.Function;
import java.util.function.Predicate;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.Map;
import net.ollie.sc4j.imposed.Unique;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Keyed<K, V> {

    @Nonnull
    Unique<K> keys();

    default boolean containsKey(final K key) {
        return this.keys().contains(key);
    }

    @Nonnull
    Collection<V> values();

    default boolean containsValue(final V value) {
        return this.values().contains(value);
    }

    default boolean isEmpty() {
        return this.keys().isEmpty() || this.values().isEmpty();
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

    interface Single<K, V> extends Keyed<K, V> {

        @CheckForNull
        V get(@Nonnull Object key);

    }

    interface Dual<K1, K2, V> extends Keyed.Single<Map.Entry<K1, K2>, V> {

        V get(Object key1, Object key2);

        default V get(final Map.Entry<? extends K1, ? extends K2> entry) {
            return this.get(entry.key(), entry.value());
        }

        @Override
        default V get(final Object object) {
            if (object instanceof Map.Entry) {
                final Map.Entry<?, ?> that = (Map.Entry) object;
                return this.get(that.key(), that.value());
            } else {
                return null;
            }
        }

    }

    interface Multiple<K, V> extends Keyed<K, V> {

        @CheckForNull
        Collection<V> get(@Nonnull Object key);

    }

}
