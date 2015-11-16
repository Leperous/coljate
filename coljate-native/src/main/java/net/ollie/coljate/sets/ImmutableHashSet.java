package net.ollie.coljate.sets;

import java.util.Iterator;

import net.ollie.coljate.ImmutableCollection;

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
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableCollection<T> with(final T element) {
        final java.util.HashSet<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableHashSet<>(copy);
    }

    @Override
    public ImmutableSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
