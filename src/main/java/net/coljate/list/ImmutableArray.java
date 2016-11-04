package net.coljate.list;

/**
 *
 * @author ollie
 */
public class ImmutableArray<T> implements Array<T>, ImmutableList<T> {

    @SuppressWarnings("unchecked")
    public static <T> ImmutableArray<T> of() {
        return EmptyArray.INSTANCE;
    }

    private final Object[] underlying;
    private final int count;

    ImmutableArray(final Object[] underlying, final int count) {
        this.underlying = underlying;
        this.count = count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(final int index) {
        return (T) underlying[index];
    }

    @Override
    public int length() {
        return underlying.length;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public ImmutableArrayIterator<T> iterator() {
        return new ImmutableArrayIterator<>(this);
    }

    @Override
    @Deprecated
    public ImmutableArray<T> immutableCopy() {
        return this;
    }

    public class ImmutableArrayIterator<T> extends ArrayIterator<T> implements ImmutableListIterator<T> {

        protected ImmutableArrayIterator(final ImmutableArray<T> array) {
            super(array);
        }

    }

}
