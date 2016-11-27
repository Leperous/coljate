package net.coljate.list;

/**
 *
 * @author ollie
 */
public class ImmutableNativeArray<T> implements ImmutableArray<T>, ImmutableList<T> {

    private final Object[] array;
    private final int count;

    ImmutableNativeArray(final Object[] array, final int count) {
        this.array = array;
        this.count = count;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public T get(final int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public ImmutableListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableNativeArray<T> prefixed(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ImmutableNativeArray<T> suffixed(final T element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MutableList<T> mutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Deprecated
    public ImmutableNativeArray<T> immutableCopy() {
        return this;
    }

}
