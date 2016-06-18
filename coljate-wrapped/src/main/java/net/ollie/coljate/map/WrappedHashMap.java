package net.ollie.coljate.map;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Ollie
 */
public abstract class WrappedHashMap<@NonNull K, @Nullable V>
        extends WrappedMap<K, V>
        implements HashMap<K, V> {

    public static <K, V> java.util.HashMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return new java.util.HashMap<>(map);
    }

    final java.util.HashMap<K, V> delegate;

    protected WrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

}
