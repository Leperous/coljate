package net.coljate.counter;

import net.coljate.collection.ImmutableCollection;
import net.coljate.map.ImmutableMap;

/**
 *
 * @author ollie
 */
public interface ImmutableCounter<T>
        extends Counter<T>, ImmutableCollection<T> {

    @Override
    ImmutableMap<T, Integer> countElements();

    @Override
    @Deprecated
    default ImmutableCounter<T> immutableCopy() {
        return this;
    }

}
