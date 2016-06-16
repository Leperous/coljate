package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public abstract class WrappedHashMap<K, V> extends WrappedMap<K, V> {

    public static <K, V> java.util.HashMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return new java.util.HashMap<>(map);
    }

    final java.util.HashMap<K, V> delegate;

    protected WrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

}
