package net.coljate.counter;

import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableSet;

/**
 *
 * @author ollie
 */
public interface ImmutableCounter<T>
        extends Counter<T>, ImmutableSet<T> {

    @Override
    ImmutableMap<T, Integer> countElements();

    @Override
    @Deprecated
    default ImmutableCounter<T> immutableCopy() {
        return this;
    }

}
