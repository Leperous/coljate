package net.coljate.counter.impl;

import java.util.Iterator;

import net.coljate.counter.Counter;
import net.coljate.map.Map;

/**
 *
 * @author Ollie
 * @see org.apache.commons.collections4.Bag
 */
public class CommonsBagCounter<T> implements Counter<T> {

    private final org.apache.commons.collections4.Bag<T> bag;

    public CommonsBagCounter(final org.apache.commons.collections4.Bag<T> bag) {
        this.bag = bag;
    }

    @Override
    public int count(final Object element) {
        return bag.getCount(element);
    }

    @Override
    public Map<T, Integer> countElements() {
        return new CommonsBagCounterMap<>(bag);
    }

    @Override
    public Iterator<T> iterator() {
        return bag.iterator();
    }

}
