package net.ollie.coljate.lists;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.lists.mixin.ImmutableNativeListMixin;

/**
 *
 * @author Ollie
 */
public class ImmutableEmptyList<T> implements ImmutableNativeListMixin<T> {

    @SuppressWarnings("rawtypes")
    private static final ImmutableEmptyList INSTANCE = new ImmutableEmptyList();

    @SuppressWarnings("unchecked")
    public static <T> ImmutableList<T> instance() {
        return INSTANCE;
    }

    protected ImmutableEmptyList() {
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
    public int size() {
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
