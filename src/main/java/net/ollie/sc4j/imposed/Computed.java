package net.ollie.sc4j.imposed;

import net.ollie.sc4j.access.Keyed;

/**
 *
 * @author Ollie
 * @param <K> input (key) type
 * @param <V> output (value) type
 */
public interface Computed<K, V>
        extends Keyed<K, V>, java.util.function.Function<K, V> {

    @Override
    default V apply(final K key) {
        return this.get(key);
    }

}
