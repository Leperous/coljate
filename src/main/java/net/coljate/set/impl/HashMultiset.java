package net.coljate.set.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.AbstractSet;
import net.coljate.set.ImmutableMultiset;
import net.coljate.set.Multiset;
import net.coljate.set.MutableMultiset;
import net.coljate.util.Iterators.EnhancedIterator;
import net.coljate.util.Suppliers;

/**
 *
 * @author ollie
 */
public class HashMultiset<T>
        extends AbstractSet<T>
        implements Multiset<T> {

    private final Map<T, Integer> map;

    protected HashMultiset(final Map<T, Integer> count) {
        this.map = count;
    }

    @Override
    public int count(final Object object) {
        return Suppliers.firstNonNull(map.getIfPresent(object), 0);
    }

    @Override
    public Map<T, Integer> countElements() {
        return map;
    }

    @Override
    public boolean contains(final Object object) {
        return Multiset.super.contains(object);
    }

    protected static boolean isPositive(final Integer integer) {
        return integer != null && integer > 0;
    }

    @Override
    public Iterator<T> iterator() {
        return this.enhancedIterator();
    }

    protected EnhancedIterator<T> enhancedIterator() {
        return new MultisetIterator();
    }

    @Override
    public MutableMultiset<T> mutableCopy() {
        return new MutableHashMultiset<>(map.mutableCopy(), true);
    }

    @Override
    public ImmutableMultiset<T> immutableCopy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private final class MultisetIterator implements EnhancedIterator<T> {

        final Iterator<Entry<T, Integer>> mapIterator = map.iterator();
        T currentElement;
        int currentCount;

        @Override
        public boolean hasNext() {
            while (currentCount == 0 && mapIterator.hasNext()) {
                final Entry<T, Integer> entry = mapIterator.next();
                currentElement = entry.key();
                currentCount = entry.value();
            }
            return currentCount > 0;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            currentCount--;
            return currentElement;
        }

        @Override
        public T current() {
            return currentElement;
        }

    }

}
