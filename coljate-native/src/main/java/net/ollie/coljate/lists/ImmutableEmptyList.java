package net.ollie.coljate.lists;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableEmptyList<T> implements ImmutableList<T> {

    @SuppressWarnings("rawtypes")
    private static final ImmutableEmptyList INSTANCE = new ImmutableEmptyList();

    @SuppressWarnings("unchecked")
    public static <T> ImmutableList<T> empty() {
        return INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        throw new IndexOutOfBoundsException("Empty list!");
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
    public ImmutableList<T> prefix(final T element) {
        return this.suffix(element);
    }

    @Override
    public ImmutableList<T> suffix(final T element) {
        return ImmutableArrayList.of(element);
    }

    @Override
    public MutableList<T> mutableCopy() {
        return MutableArrayList.of();
    }

    @Override
    public ImmutableList<T> tail() {
        return this;
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.empty();
    }

}
