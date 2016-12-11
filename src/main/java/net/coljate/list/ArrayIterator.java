package net.coljate.list;

/**
 *
 * @author Ollie
 */
public class ArrayIterator<T> implements ListIterator<T> {

    private final Array<? extends T> array;
    private int index = 0;

    protected ArrayIterator(final Array<? extends T> array) {
        this.array = array;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T previous() {
        return array.get(--index);
    }

    @Override
    public boolean hasNext() {
        return index < array.count();
    }

    @Override
    public T next() {
        return array.get(index++);
    }

}
