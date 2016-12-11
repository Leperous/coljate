package net.coljate.collection.lazy;

import java.util.Iterator;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.util.Iterators;

/**
 *
 * @author Ollie
 */
public class LazyFilteredCollection<T> implements LazyCollection<T> {

    private final Collection<? extends T> collection;
    private final Predicate<? super T> predicate;

    protected LazyFilteredCollection(final Collection<? extends T> collection, final Predicate<? super T> predicate) {
        this.collection = collection;
        this.predicate = predicate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(final Object object) {
        return collection.contains(object)
                && predicate.test((T) object);
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.filter(collection.iterator(), predicate);
    }

}
