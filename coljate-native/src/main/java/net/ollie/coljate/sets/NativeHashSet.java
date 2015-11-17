package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
public class NativeHashSet<T> extends NativeSet<T> {

    private final java.util.HashSet<T> delegate;

    protected NativeHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    protected java.util.HashSet<T> copyDelegate() {
        return copyIntoHashSet(delegate);
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return MutableHashSet.copyOf(delegate);
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        return ImmutableHashSet.copyOf(delegate);
    }

}
