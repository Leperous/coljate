package net.ollie.sc4j.imposed;

import net.ollie.sc4j.access.Keyed;

/**
 *
 * @author Ollie
 */
public interface Computed<K, V>
        extends Keyed<K, V>, java.util.function.Function<K, V> {

    @Override
    default V apply(final K key) {
        return this.get(key);
    }

}
