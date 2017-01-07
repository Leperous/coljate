package net.coljate.counter.impl;

import java.util.Iterator;

import com.google.common.collect.Multiset;

import net.coljate.collection.Collection;
import net.coljate.counter.AbstractCounter;
import net.coljate.map.AbstractMap;
import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public class GuavaCounter<T>
        extends AbstractCounter<T> {

    public static <T> GuavaCounter<T> viewOf(final Multiset<T> multiset) {
        return new GuavaCounter<>(multiset);
    }

    private final Multiset<T> multiset;
    private Map<T, Integer> elements;

    protected GuavaCounter(final Multiset<T> multiset) {
        this.multiset = multiset;
    }

    @Override
    public int count(final Object element) {
        return multiset.count(element);
    }

    @Override
    public Map<T, Integer> countElements() {
        return elements == null
                ? elements = new GuavaCounterMap()
                : elements;
    }

    @Override
    public Iterator<T> iterator() {
        return multiset.iterator();
    }

    private final class GuavaCounterMap extends AbstractMap<T, Integer> {

        private Set<T> keys;
        private Collection<Integer> values;

        @Override
        @SuppressWarnings("unchecked")
        public Entry<T, Integer> getEntry(final Object key) {
            final int count = GuavaCounter.this.count(key);
            return count > 0
                    ? Entry.of((T) key, count)
                    : null;
        }

        @Override
        public Set<T> keys() {
            return keys == null
                    ? keys = Set.viewOf(multiset.elementSet())
                    : keys;
        }

        @Override
        public Collection<Integer> values() {
            return values == null
                    ? values = Collection.viewOf(multiset.entrySet()).transform(Multiset.Entry::getCount)
                    : values;
        }

    }

}
