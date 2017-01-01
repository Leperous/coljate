package net.coljate.map.primitive;

import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
public interface LongKeyMap<V> extends Map<Long, V> {

    V get(long key);

    @Override
    default V get(final Long key) {
        return this.get(key.longValue());
    }

}
