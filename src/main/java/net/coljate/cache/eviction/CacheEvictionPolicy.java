package net.coljate.cache.eviction;

/**
 *
 * @author ollie
 */
public interface CacheEvictionPolicy {

    void notifyRead(Object key);

    Object notifyWrite(Object key);

    void notifyRemove(Object key);

    void notifyClear();

}
