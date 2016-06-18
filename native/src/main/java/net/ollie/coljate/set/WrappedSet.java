package net.ollie.coljate.set;

import static java.util.Objects.requireNonNull;

import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.set.mixin.CopiedToHashSet;
import net.ollie.coljate.set.mixin.WrapsSet;

/**
 *
 * @author Ollie
 */
public class WrappedSet<T>
        extends WrappedCollection<T>
        implements WrapsSet<T>, CopiedToHashSet<T> {

    private final java.util.Set<T> delegate;

    public WrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
    }

    protected java.util.Set<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.Set<T> copyDelegate() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    public Set<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return CopiedToHashSet.super.mutableCopy();
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        return CopiedToHashSet.super.immutableCopy();
    }

}
