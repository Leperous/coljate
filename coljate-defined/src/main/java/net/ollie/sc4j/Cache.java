package net.ollie.sc4j;

import java.util.function.Function;

import net.ollie.sc4j.imposed.Cached;
import net.ollie.sc4j.imposed.Computed;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * A mutable map that may also calculate (and possibly, but not necessarily, store) values on-demand.
 *
 * @author Ollie
 * @param <K> key type
 * @param <V> value type
 * @see Map
 */
public interface Cache<K, V>
        extends Cached.Mutable<K, V>, Computed<K, V> {

    @CheckForNull
    V getIfPresent(K key);

    @CheckReturnValue
    @Nonnull
    <V2> Cache<K, V2> recompute(Function<? super V, ? extends V2> function);

    @Override
    Map.Immutable<K, V> immutableCopy();

}
