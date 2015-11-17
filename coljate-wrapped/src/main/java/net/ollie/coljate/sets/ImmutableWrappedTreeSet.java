package net.ollie.coljate.sets;

import java.util.Comparator;
import java.util.SortedSet;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedTreeSet<T> extends WrappedTreeSet<T> implements ImmutableSortedSet<T> {

    ImmutableWrappedTreeSet(SortedSet<T> delegate, Comparator<? super T> comparator) {
        super(delegate, comparator);
    }

    @Override
    public ImmutableWrappedTreeSet<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableWrappedTreeSet<T> with(final T element) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

}
