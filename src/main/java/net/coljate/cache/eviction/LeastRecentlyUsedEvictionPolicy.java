package net.coljate.cache.eviction;

import java.util.Iterator;

import net.coljate.list.impl.WrappedLinkedHashSetQueue;
import net.coljate.util.iterator.Iterators;

/**
 * Evicts the key(s) last written or read.
 *
 * @author Ollie
 */
public class LeastRecentlyUsedEvictionPolicy implements CacheEvictionPolicy {

    private final WrappedLinkedHashSetQueue<Object> queue = new WrappedLinkedHashSetQueue<>();
    private final int capacity;

    public LeastRecentlyUsedEvictionPolicy(final int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Iterator<Object> notifyRead(final Object key) {
        queue.add(key);
        return this.determineEvictions();
    }

    @Override
    public Iterator<Object> notifyWrite(final Object key) {
        queue.add(key);
        return this.determineEvictions();
    }

    private Iterator<Object> determineEvictions() {
        final int evictions = queue.count() - capacity;
        return evictions > 0
                ? Iterators.first(queue.iterator(), evictions)
                : Iterators.empty();
    }

    @Override
    public Iterator<Object> notifyRemove(final Object key) {
        queue.removeFirst(key);
        return Iterators.empty();
    }

    @Override
    public void notifyClear() {
        queue.clear();
    }

}
