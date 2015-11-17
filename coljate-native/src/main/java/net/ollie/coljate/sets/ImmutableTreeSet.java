package net.ollie.coljate.sets;

import java.util.Comparator;
import java.util.SortedSet;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableTreeSet<T> extends NativeTreeSet<T> implements ImmutableSortedSet<T> {

    ImmutableTreeSet(SortedSet<T> delegate, Comparator<? super T> comparator) {
        super(delegate, comparator);
    }

    @Override
    public ImmutableTreeSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableTreeSet<T> with(final T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
