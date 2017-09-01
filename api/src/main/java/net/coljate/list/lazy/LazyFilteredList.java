package net.coljate.list.lazy;

import java.util.function.Function;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
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

    public static <T> Function<Collection<T>, ? extends LazyFilteredList<T>> filter(final Predicate<? super T> predicate) {
        return list -> new LazyFilteredList<>(List.copyOrCast(list), predicate);
    }

    public LazyFilteredList(final List<? extends T> collection, final Predicate<? super T> predicate) {
        super(collection, predicate);
    }

    @Override
    public ListIterator<T> iterator() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ListIterator<T> reverseIterator() {
        throw new UnsupportedOperationException(); //TODO
    }
    
    @Override
    public T last() {
        throw new UnsupportedOperationException(); //TODO
    }

}
