package net.ollie.coljate.sets;

import java.util.Iterator;
import static java.util.Objects.requireNonNull;

import net.ollie.coljate.WrappedCollection;
import net.ollie.coljate.sets.mixin.WrapsSet;

/**
 *
 * @author Ollie
 */
public class WrappedSet<T>
        extends WrappedCollection<T>
        implements WrapsSet<T> {

    public static <T> java.util.HashSet<T> copyIntoHashSet(final java.util.Collection<? extends T> collection) {
        return new java.util.HashSet<>(collection);
    }

    public static <T> java.util.HashSet<T> copyIntoHashSet(final Iterator<? extends T> iterator) {
        final java.util.HashSet<T> hashSet = new java.util.HashSet<>();
        iterator.forEachRemaining(hashSet::add);
        return hashSet;
    }

    final java.util.Set<T> delegate;

    public WrappedSet(final java.util.Set<T> delegate) {
        super(delegate);
        this.delegate = requireNonNull(delegate);
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
        return MutableWrappedHashSet.copyOf(delegate);
    }

    @Override
    public ImmutableSet<T> immutableCopy() {
        return ImmutableWrappedHashSet.copyOf(delegate);
    }

}
