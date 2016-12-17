package net.coljate.set;

import net.coljate.map.ImmutableMap;

/**
 *
 * @author ollie
 */
public interface ImmutableMultiset<T>
        extends Multiset<T>, ImmutableSet<T> {

    @Override
    ImmutableMap<T, Integer> countElements();

    @Override
    @Deprecated
    default ImmutableMultiset<T> immutableCopy() {
        return this;
    }

}
