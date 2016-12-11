package net.coljate.collection.lazy;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import net.coljate.collection.Collection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;

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
    public Iterator<T> iterator() {
        return new FilteredIterator();
    }

    @Override
    public MutableCollection<? extends T> mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableCollection<? extends T> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    protected class FilteredIterator implements Iterator<T> {

        private final Iterator<? extends T> iterator = collection.iterator();
        private T next;
        private boolean needsNext;

        @Override
        public boolean hasNext() {
            if (needsNext) {
                while (iterator.hasNext()) {
                    final T next = iterator.next();
                    if (predicate.test(next)) {
                        this.next = next;
                        needsNext = false;
                        return true;
                    }
                }
                needsNext = true;
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            needsNext = true;
            return next;
        }

    }

}
