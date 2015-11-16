package net.ollie.coljate.lists;

import java.util.Iterator;

/**
 *
 * @author Ollie
 */
public class ImmutableArrayList<T> extends NativeArrayList<T> implements ImmutableList<T> {

    public static <T> ImmutableList<T> copyOf(final java.util.Collection<? extends T> collection) {
        return new ImmutableArrayList<>(copyToList(collection));
    }

    ImmutableArrayList(final java.util.ArrayList<T> delegate) {
        super(delegate);
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableList<T> with(final T element) {
        final java.util.ArrayList<T> copy = this.copyDelegate();
        copy.add(element);
        return new ImmutableArrayList<>(copy);
    }

    @Override
    public ImmutableList<T> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

}
