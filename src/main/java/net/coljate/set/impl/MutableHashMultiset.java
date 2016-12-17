package net.coljate.set.impl;

import java.util.Iterator;

import net.coljate.map.MutableMap;
import net.coljate.set.MutableMultiset;
import net.coljate.util.Iterators.EnhancedIterator;

/**
 *
 * @author ollie
 */
public class MutableHashMultiset<T>
        extends HashMultiset<T>
        implements MutableMultiset<T> {

    public static <T> MutableHashMultiset<T> copyOf(final Iterable<? extends T> iterable) {
        final MutableHashMultiset<T> set = new MutableHashMultiset<>(MutableMap.createHashMap(), true);
        iterable.forEach(set::add);
        return set;
    }

    private final MutableMap<T, Integer> map;
    private final boolean removeZeros;

    protected MutableHashMultiset(final MutableMap<T, Integer> map, boolean removeZeros) {
        super(map);
        this.map = map;
        this.removeZeros = removeZeros;
    }

    @Override
    public boolean add(final T element) {
        this.increment(element);
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean removeAll(final Object element) {
        return isPositive(map.evict(element));
    }

    @Override
    public int increment(final T element, final int amount) {
        return map.compute(element, (k, count) -> increment(count, amount));
    }

    @Override
    public int decrement(final T element, final int amount) {
        final int decremented = map.compute(element, (k, count) -> decrement(count, amount));
        if (decremented == 0 && removeZeros) {
            map.evict(element);
        }
        return decremented;
    }

    @Override
    public void clear() {
        map.clear();
    }

    private static int increment(final Integer integer, final int amount) {
        return integer == null ? amount : integer + amount;
    }

    private static int decrement(final Integer integer, final int amount) {
        return isPositive(integer) ? Math.max(0, integer - amount) : 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MutableHashMultisetIterator();
    }

    @Override
    public MutableMultiset<T> mutableCopy() {
        return new MutableHashMultiset<>(map.mutableCopy(), removeZeros);
    }

    private class MutableHashMultisetIterator implements EnhancedIterator<T> {

        final EnhancedIterator<T> delegate = MutableHashMultiset.this.enhancedIterator();

        @Override
        public boolean hasNext() {
            return delegate.hasNext();
        }

        @Override
        public T next() {
            return delegate.next();
        }

        @Override
        public T current() {
            return delegate.current();
        }

        @Override
        public void remove() {
            if (!this.hasNext()) {
                throw new IllegalStateException();
            }
            MutableHashMultiset.this.decrement(this.current());
        }

    }

}
