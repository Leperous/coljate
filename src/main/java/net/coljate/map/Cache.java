package net.coljate.map;

import net.coljate.util.Functions;

/**
 * A type of {@link Map} that computes and caches its values when getters are
 * called.
 *
 * @author ollie
 */
public interface Cache<K, V> extends Map<K, V> {

    Entry<K, V> entryIfPresent(Object key);

    default V getIfPresent(final Object key) {
        return Functions.ifNonNull(this.entryIfPresent(key), Entry::value);
    }

}
