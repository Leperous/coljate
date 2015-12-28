package net.ollie.coljate.theory;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author ollie
 * @param <K>
 * @param <V>
 */
public interface Associative<@NonNull K, @Nullable V>
        extends PartialFunction<K, V> {

    V get(K key);

    @Override
    default V apply(final K input) {
        return this.get(input);
    }

}
