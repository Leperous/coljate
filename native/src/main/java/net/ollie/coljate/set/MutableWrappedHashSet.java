package net.ollie.coljate.set;

import java.io.Serializable;

import net.ollie.coljate.Collection;
import static net.ollie.coljate.set.mixin.WrapsHashSet.copyIntoHashSet;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashSet<T>
        extends MutableWrappedSet<T>
        implements HashSet<T>, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @SafeVarargs
    public static <T> MutableWrappedHashSet<T> copyOf(final T... array) {
        return viewOf(copyIntoHashSet(array));
    }

    public static <T> MutableWrappedHashSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return viewOf(copyIntoHashSet(collection));
    }

    public static <T> MutableWrappedHashSet<T> copyOf(final Collection<? extends T> collection) {
        return viewOf(copyIntoHashSet(collection));
    }

    public static <T> MutableWrappedHashSet<T> viewOf(final java.util.HashSet<T> set) {
        return new MutableWrappedHashSet<>(set);
    }

    private final java.util.HashSet<T> delegate;

    public MutableWrappedHashSet() {
        this(new java.util.HashSet<>());
    }

    MutableWrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public java.util.HashSet<T> delegate() {
        return delegate;
    }

    @Override
    public java.util.HashSet<T> copyDelegate() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public MutableWrappedHashSet<T> mutableCopy() {
        return new MutableWrappedHashSet<>(this.copyDelegate());
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public MutableWrappedHashSet<T> clone() {
        return this.mutableCopy();
    }

}
