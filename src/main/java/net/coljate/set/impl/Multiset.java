package net.coljate.set.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.coljate.map.Entry;
import net.coljate.map.MutableMap;
import net.coljate.set.AbstractSet;
import net.coljate.set.MutableSet;
import net.coljate.util.Suppliers;

/**
 *
 * @author ollie
 */
public class Multiset<T>
        extends AbstractSet<T>
        implements MutableSet<T> {

    public static <T> Multiset<T> copyOf(final Iterable<? extends T> iterable) {
        final Multiset<T> set = new Multiset<>(MutableMap.createHashMap(), true);
        iterable.forEach(set::increment);
        return set;
    }

    private final MutableMap<T, Integer> map;
    private final boolean removeZeros;

    protected Multiset(final MutableMap<T, Integer> count, final boolean removeZeros) {
        this.map = count;
        this.removeZeros = removeZeros;
    }

    public int count(final Object object) {
        return Suppliers.firstNonNull(map.getIfPresent(object), 0);
    }

    @Override
    public boolean contains(final Object object) {
        final Entry<T, Integer> entry = map.entry(object);
        return entry != null
                && isPositive(entry.value());
    }

    public int increment(final T element) {
        return map.compute(element, (k, count) -> increment(count));
    }

    public int decrement(final T element) {
        final int decremented = map.compute(element, (k, count) -> decrement(count));
        if (decremented == 0 && removeZeros) {
            map.evict(element);
        }
        return decremented;
    }

    @Override
    @Deprecated
    public boolean add(final T element) {
        this.increment(element);
        return true;
    }

    @Override
    @Deprecated
    public boolean remove(final Object element) {
        return this.removeFirst(element);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean removeFirst(final Object element) {
        final Entry<T, Integer> entry = map.entry(element);
        if (entry == null) {
            return false;
        }
        final int decrement = this.decrement(entry.key());
        return decrement == 0 && !isPositive(entry.value());
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean removeAll(final Object element) {
        return isPositive(map.evict(element));
    }

    private static boolean isPositive(final Integer integer) {
        return integer != null && integer > 0;
    }

    private static int increment(final Integer integer) {
        return integer == null ? 1 : integer + 1;
    }

    private static int decrement(final Integer integer) {
        return isPositive(integer) ? integer - 1 : 0;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return new MultisetIterator();
    }

    private final class MultisetIterator implements Iterator<T> {

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
        public void remove() {
            if (!this.hasNext()) {
                throw new IllegalStateException();
            }
            Multiset.this.decrement(currentElement);
        }

    }

}
