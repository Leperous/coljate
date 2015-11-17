package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
public class WrappedHashSet<T> extends WrappedSet<T> {

    private final java.util.HashSet<T> delegate;

    protected WrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    protected java.util.HashSet<T> copyDelegate() {
        return copyIntoHashSet(delegate);
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return MutableWrappedHashSet.copyOf(delegate);
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        return ImmutableWrappedHashSet.copyOf(delegate);
    }

}
