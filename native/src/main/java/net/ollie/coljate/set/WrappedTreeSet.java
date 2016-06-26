package net.ollie.coljate.set;

import java.io.Serializable;

import net.ollie.coljate.set.mixin.WrapsTreeSet;

/**
 *
 * @author Ollie
 */
public class WrappedTreeSet<T>
        extends WrappedSortedSet<T>
        implements WrapsTreeSet<T>, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private final java.util.TreeSet<T> delegate;

    protected WrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    protected java.util.TreeSet<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.TreeSet<T> copyDelegate() {
        return new java.util.TreeSet<>(delegate);
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public WrappedTreeSet<T> clone() {
        return new WrappedTreeSet<>(this.copyDelegate());
    }

}
