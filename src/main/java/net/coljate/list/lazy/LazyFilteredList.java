package net.coljate.list.lazy;

import java.util.function.Predicate;

import net.coljate.collection.lazy.LazyFilteredCollection;
import net.coljate.list.List;
import net.coljate.list.ListIterator;

/**
 *
 * @author Ollie
 */
public class LazyFilteredList<T>
        extends LazyFilteredCollection<T>
        implements LazyList<T> {

    protected LazyFilteredList(final List<? extends T> collection, final Predicate<? super T> predicate) {
        super(collection, predicate);
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public T last() {
        throw new UnsupportedOperationException(); //TODO
    }

}
