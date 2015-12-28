package net.ollie.coljate.lists;

import net.ollie.coljate.UnmodifiableIterator;

/**
 *
 * @author Ollie
 */
public class ImmutableEmptyArray<T>
        extends AbstractList<T>
        implements ImmutableArray<T> {

    @SuppressWarnings("rawtypes")
    private static final ImmutableEmptyArray INSTANCE = new ImmutableEmptyArray();

    @SuppressWarnings("unchecked")
    public static <T> ImmutableArray<T> get() {
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
    public MutableArray<T> mutableCopy() {
        //return MutableArrayList.of();
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableArray<T> tail() {
        return this;
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.empty();
    }

    @Override
    public int capacity() {
        return 0;
    }

}
