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
    EvictionList notifyRead(Object key);

    @Nonnull
    @CheckReturnValue
    EvictionList notifyWrite(Object key);

    @Nonnull
    @CheckReturnValue
    EvictionList notifyRemove(Object key);

    void notifyClear();

    interface EvictionList extends Iterator<Object> {

    }

}
