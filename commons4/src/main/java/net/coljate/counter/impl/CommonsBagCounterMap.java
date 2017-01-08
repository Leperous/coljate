package net.coljate.counter.impl;

import net.coljate.collection.Collection;
import net.coljate.map.AbstractEntry;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 * @since 1.0
 * @see org.apache.commons.collections4.Bag
 * @see CommonsBagCounter
 */
public class CommonsBagCounterMap<T> extends AbstractMap<T, Integer> {

    private final org.apache.commons.collections4.Bag<T> bag;
    private Set<T> keys;

    protected CommonsBagCounterMap(final org.apache.commons.collections4.Bag<T> bag) {
        this.bag = bag;
    }

    @Override
    @SuppressWarnings("element-type-mismatch")
    public boolean containsKey(final Object key) {
        return bag.contains(key);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Entry<T, Integer> getEntry(final Object key) {
        return this.containsKey(key)
                ? new CommonsBagCounterMapEntry((T) key)
                : null;
    }

    @Override
    public Set<T> keys() {
        return keys == null
                ? keys = Set.viewOf(bag.uniqueSet())
                : keys;
    }

    @Override
    public Collection<Integer> values() {
        return this.keys().transform(bag::getCount);
    }

    private final class CommonsBagCounterMapEntry extends AbstractEntry<T, Integer> {

        private final T key;

        public CommonsBagCounterMapEntry(T key) {
            this.key = key;
        }

        @Override
        public T key() {
            return key;
        }

        @Override
        public Integer value() {
            return bag.getCount(key);
        }

    }

}
