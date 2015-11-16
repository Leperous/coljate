package net.ollie.coljate.sets;

import java.util.Collection;
import java.util.Iterator;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Ollie
 */
public abstract class NativeHashSet<T> implements Set<T> {

    public static <T> java.util.HashSet<T> copyIntoHashSet(final Collection<? extends T> collection) {
        return new java.util.HashSet<>(collection);
    }

    public static <T> java.util.HashSet<T> copyIntoHashSet(final Iterator<? extends T> iterator) {
        final java.util.HashSet<T> hashSet = new java.util.HashSet<>();
        iterator.forEachRemaining(hashSet::add);
        return hashSet;
    }

    private final java.util.HashSet<T> delegate;

    protected NativeHashSet(final java.util.HashSet<T> delegate) {
        this.delegate = requireNonNull(delegate);
    }

    protected java.util.HashSet<T> copyDelegate() {
        return copyIntoHashSet(delegate);
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean contains(final Object object) {
        return delegate.contains(object);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public MutableSet<T> mutableCopy() {
        return MutableHashSet.viewOf(this.copyDelegate());
    }

}
