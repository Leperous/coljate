package net.ollie.coljate.list;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.list.mixin.CopiedToArray;
import net.ollie.coljate.list.mixin.WrapsImmutableList;

/**
 *
 * @author Ollie
 */
public class ImmutableWrappedEmptyArray<T>
        extends AbstractList<T>
        implements ImmutableArray<T>, WrapsImmutableList<T>, CopiedToArray<T> {

    @SuppressWarnings("rawtypes")
    private static final ImmutableWrappedEmptyArray INSTANCE = new ImmutableWrappedEmptyArray();

    @SuppressWarnings("unchecked")
    public static <T> ImmutableWrappedEmptyArray<T> empty() {
        return INSTANCE;
    }

    protected ImmutableWrappedEmptyArray() {
    }

    @Override
    public T head() {
        return null;
    }

    @Override
    public ImmutableWrappedEmptyArray<T> tail() {
        return this;
    }

    @Override
    public T get(final int index) {
        throw new IndexOutOfBoundsException("List is empty.");
    }

    @Override
    public boolean contains(final Object object) {
        return false;
    }

    @Override
    public int capacity() {
        return 0;
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
        return UnmodifiableIterator.none();
    }

    @Override
    public MutableArray<T> mutableCopy() {
        return CopiedToArray.super.mutableCopy();
    }

    @Override
    @Deprecated
    public ImmutableWrappedEmptyArray<T> immutableCopy() {
        return this;
    }

}
