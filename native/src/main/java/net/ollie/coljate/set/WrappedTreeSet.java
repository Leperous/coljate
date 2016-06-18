package net.ollie.coljate.set;

import net.ollie.coljate.set.mixin.WrapsTreeSet;

/**
 *
 * @author Ollie
 */
public class WrappedTreeSet<T>
        extends WrappedSortedSet<T>
        implements WrapsTreeSet<T> {

    private final java.util.TreeSet<T> delegate;

    protected WrappedTreeSet(final java.util.TreeSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.TreeSet<T> copyDelegate() {
        return new java.util.TreeSet<>(delegate);
    }

}
