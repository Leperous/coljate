package net.coljate.list;

/**
 *
 * @author ollie
 */
public class ImmutableSingletonList<T> implements ImmutableList<T> {

    private final T element;

    public ImmutableSingletonList(final T element) {
        this.element = element;
    }

    @Override
    public ImmutableListIterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
