package net.ollie.sc4j.imposed;

import java.util.function.Function;
import java.util.function.Supplier;

import net.ollie.sc4j.Collection;
import net.ollie.sc4j.access.Iteratable;
import net.ollie.sc4j.access.Keyed;
import net.ollie.sc4j.utils.Iterables.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public interface Cached<K, V>
        extends Keyed<K, V>, Iteratable<V> {

    @Override
    Iteratable<V> values();

    @Override
    default boolean contains(final Object object) {
        return this.values().contains(object);
    }

    @Override
    default Object[] toRawArray() {
        return this.values().toRawArray();
    }

    @Override
    <V2> Cached<K, V2> mapValues(Function<? super V, ? extends V2> function);

    @Override
    default <V2> Collection<V2> map(final Function<? super V, ? extends V2> function) {
        return this.mapValues(function);
    }

    @Override
    Cached.Mutable<K, V> mutable();

    @Override
    Cached.Immutable<K, V> immutable();

    /**
     *
     * @param <K>
     * @param <V>
     */
    interface Mutable<K, V>
            extends Cached<K, V>, Iteratable.Mutable<V> {

        V put(K key, V value);

        Cached<K, V> putAll(Cached<K, V> keyed);

        V putIfAbsent(K key, V value);

        default V putIfAbsent(K key, Supplier<? extends V> supplier) {
            final V value = this.get(key);
            return value == null
                    ? this.putIfAbsent(key, supplier.get())
                    : value;
        }

        boolean replace(K key, V oldValue, V newValue);

        V remove(Object key);

    }

    interface Immutable<K, V>
            extends Cached<K, V>, Iteratable.Immutable<V> {

        @Override
        default Cached.Immutable<K, V> immutable() {
            return this;
        }

        @Override
        UnmodifiableIterator<V> iterator();

    }

}
