package net.ollie.coljate.set;

import java.util.HashSet;

import net.ollie.coljate.Collection;
import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.set.ImmutableSet;
import net.ollie.coljate.set.mixin.WrapsHashSet;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedHashSet<T> extends WrappedSet<T> implements ImmutableSet<T>, WrapsHashSet<T> {

    public static <T> ImmutableSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableWrappedHashSet<>(new java.util.HashSet<>(collection));
    }

    public static <T> ImmutableSet<T> copyOf(final Collection<? extends T> collection) {
        throw new UnsupportedOperationException(); //TODO
    }

    private final java.util.HashSet<T> delegate;

    ImmutableWrappedHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public HashSet<T> copyDelegate() {
        return new java.util.HashSet<>(delegate);
    }

    @Override
    public ImmutableSet<T> with(final T element) {
        if (this.contains(element)) {
            return this;
        }
        final java.util.HashSet<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableWrappedHashSet<>(copy);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return new DelegatedUnmodifiableIterator<>(super.iterator());
    }

    @Override
    public ImmutableSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
