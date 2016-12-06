package net.coljate.list;

import java.util.NoSuchElementException;

import net.coljate.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public interface ImmutableListIterator<T> extends ListIterator<T>, UnmodifiableIterator<T> {

    @SuppressWarnings("unchecked")
    static <T> ImmutableListIterator<T> empty() {
        return EMPTY;
    }

    @SuppressWarnings("rawtypes")
    ImmutableListIterator EMPTY = new ImmutableListIterator() {

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }

    };

}
