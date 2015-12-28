package net.ollie.coljate.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author ollie
 */
public class Iterators {

    private static final Iterator NONE = new Iterator() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException();
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> none() {
        return NONE;
    }

}
