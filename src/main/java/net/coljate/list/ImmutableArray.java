package net.coljate.list;

/**
 *
 * @author ollie
 */
public interface ImmutableArray<T>
        extends Array<T>, ImmutableList<T> {

    @Override
    default ImmutableListIterator<T> iterator() {
        return new ImmutableArrayIterator<>(this);
    }

    @Override
    @Deprecated
    default ImmutableArray<T> immutableCopy() {
        return this;
    }

    static class ImmutableArrayIterator<T> extends ArrayIterator<T> implements ImmutableListIterator<T> {

        public ImmutableArrayIterator(Array<? extends T> array) {
            super(array);
        }

    }

}
