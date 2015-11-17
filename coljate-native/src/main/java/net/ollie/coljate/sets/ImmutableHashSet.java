package net.ollie.coljate.sets;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.utils.DelegatedUnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableHashSet<T> extends NativeHashSet<T> implements ImmutableSet<T> {

    public static <T> ImmutableSet<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableHashSet<>(new java.util.HashSet<>(collection));
    }

    ImmutableHashSet(final java.util.HashSet<T> delegate) {
        super(delegate);
    }

    @Override
    public ImmutableSet<T> with(final T element) {
        if (this.contains(element)) {
            return this;
        }
        final java.util.HashSet<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableHashSet<>(copy);
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
