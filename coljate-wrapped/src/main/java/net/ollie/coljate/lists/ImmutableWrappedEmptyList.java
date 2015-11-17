package net.ollie.coljate.lists;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.lists.mixin.WrapsImmutableList;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedEmptyList<T>
        extends AbstractList<T>
        implements WrapsImmutableList<T> {

    @SuppressWarnings("rawtypes")
    private static final ImmutableWrappedEmptyList INSTANCE = new ImmutableWrappedEmptyList();

    @SuppressWarnings("unchecked")
    public static <T> ImmutableList<T> empty() {
        return INSTANCE;
    }

    protected ImmutableWrappedEmptyList() {
    }

    @Override
    public T head() {
        return null;
    }

    @Override
    public ImmutableList<T> tail() {
        return this;
    }

    @Override
    public T get(final int index) {
        throw new IndexOutOfBoundsException("Empty list!");
    }

    @Override
    public boolean contains(final Object object) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.empty();
    }

}
