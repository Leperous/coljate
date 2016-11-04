package net.coljate.list;

/**
 *
 * @author ollie
 */
public class ArrayIterator<T> implements ListIterator<T> {

    private final Array<T> array;
    private int index;

    public ArrayIterator(final Array<T> array) {
        this.array = array;
    }

    protected int currentIndex() {
        return index;
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
