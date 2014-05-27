package net.ollie.sc4j;

import javax.annotation.CheckForNull;

import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.imposed.Computed;

/**
 * A mutable map that may also calculate (and store) values on-demand.
 *
 * @author Ollie
 * @param <K> key type
 * @param <V> value type
 */
public interface Cache<K, V>
        extends Cached.Mutable<K, V>, Computed<K, V> {

    @CheckForNull
    V getIfPresent(K key);

    @Override
    Map.Immutable<K, V> immutable();

}
