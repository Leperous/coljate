package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashMap<K, V>
        extends MutableWrappedMap<K, V>
        implements MutableMap<K, V>, HashMap<K, V> {

    public static final int DEFAULT_CAPACITY = 10;

    public static <K, V> MutableWrappedHashMap<K, V> create() {
        return create(DEFAULT_CAPACITY);
    }

    public static <K, V> MutableWrappedHashMap<K, V> create(final int initialCapacity) {
        return new MutableWrappedHashMap<>(new java.util.HashMap<>(initialCapacity));
    }

    public static <K, V> MutableWrappedHashMap<K, V> copyOf(final java.util.Map<K, V> map) {
        return new MutableWrappedHashMap<>(HashMap.copyOf(map));
    }

    public static <K, V> MutableWrappedHashMap<K, V> viewOf(final java.util.HashMap<K, V> map) {
        return new MutableWrappedHashMap<>(map);
    }

    protected MutableWrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
    }

}
