package net.coljate.collection.impl;

import java.util.NoSuchElementException;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.AbstractCollection;
import net.coljate.collection.ImmutableCollection;
import net.coljate.collection.MutableCollection;

/**
 *
 * @author ollie
 */
public class EmptyCollection<T>
        extends AbstractCollection<T>
        implements ImmutableCollection<T> {
    
    private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
    private static final EmptyCollection EMPTY_COLLECTION = new EmptyCollection();
    
    @SuppressWarnings("unchecked")
    public static <T> EmptyCollection<T> instance() {
        return EMPTY_COLLECTION;
    }
    
    @Override
    public MutableCollection<? extends T> mutableCopy() {
        return MutableCollection.copyOf();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public UnmodifiableIterator<T> iterator() {
        return EMPTY_ITERATOR;
    }
    
    @Override
    public SingletonCollection<T> with(final T element) {
        return ImmutableCollection.copyOf(element);
    }
    
    protected static class EmptyIterator<T> implements UnmodifiableIterator<T> {
        
        @Override
        public boolean hasNext() {
            return false;
        }
        
        @Override
        public T next() {
            throw new NoSuchElementException();
        }
        
    }
    
}
