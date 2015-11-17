package net.ollie.coljate.maps;

/**
 *
 * @author Ollie
 */
public abstract class WrappedHashMap<K, V> extends WrappedMap<K, V> {

    public static <K, V> java.util.HashMap<K, V> copyIntoHashMap(final java.util.Map<K, V> map) {
        return new java.util.HashMap<>(map);
    }

    protected WrappedHashMap(final java.util.HashMap<K, V> delegate) {
        super(delegate);
    }

    @Override
    public MutableMap<K, V> mutableCopy() {
        return MutableWrappedHashMap.copyOf(delegate);
    }

    @Override
    public ImmutableMap<K, V> immutableCopy() {
        return ImmutableWrappedHashMap.copyOf(delegate);
    }

}
