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
        return EmptyImmutableListIterator.INSTANCE;
    }

    @SuppressWarnings("rawtypes")
    class EmptyImmutableListIterator<T> extends EmptyUnmodifiableIterator<T> implements ImmutableListIterator<T> {

        static final EmptyImmutableListIterator INSTANCE = new EmptyImmutableListIterator();

        protected EmptyImmutableListIterator() {
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            throw new NoSuchElementException();
        }

    };

}
