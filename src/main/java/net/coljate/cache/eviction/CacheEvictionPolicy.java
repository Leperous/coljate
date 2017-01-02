package net.coljate.cache.eviction;

import java.util.Iterator;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface CacheEvictionPolicy {

    @Nonnull
    @CheckReturnValue
    Iterator<Object> notifyRead(Object key);

    @Nonnull
    @CheckReturnValue
    Iterator<Object> notifyWrite(Object key);

    @Nonnull
    @CheckReturnValue
    Iterator<Object> notifyRemove(Object key);

    void notifyClear();

    static CacheEvictionPolicy leastRecentlyUsed(final int capacity) {
        return new LeastRecentlyUsedEvictionPolicy(capacity);
    }

}
