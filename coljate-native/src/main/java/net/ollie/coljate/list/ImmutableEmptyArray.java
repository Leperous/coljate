package net.ollie.coljate.list;

import net.ollie.coljate.UnmodifiableIterator;
import net.ollie.coljate.theory.feature.Empty;

/**
 *
 * @author Ollie
 */
public class ImmutableEmptyArray<T>
        extends AbstractList<T>
        implements ImmutableArray<T>, Empty<T> {

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
    public ImmutableList<T> prefixed(final T element) {
        return this.suffixed(element);
    }

    @Override
    public ImmutableList<T> suffixed(final T element) {
        return ImmutableArrayList.of(element);
    }

    @Override
    public MutableArray<T> mutableCopy() {
        //return MutableArrayList.of();
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableEmptyArray<T> tail() {
        return this;
    }

    @Override
    public boolean contains(Object object) {
        return Empty.super.contains(object);
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.none();
    }

    @Override
    public int capacity() {
        return 0;
    }

}
