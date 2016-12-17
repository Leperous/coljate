package net.coljate.set;

import net.coljate.map.Map;

/**
 *
 * @author ollie
 */
public interface Multiset<T> extends Set<T> {

    int count(Object element);

    @Override
    default boolean contains(final Object element) {
        return this.count(element) > 0;
    }

    Map<T, Integer> countElements();

    @Override
    MutableMultiset<T> mutableCopy();

    @Override
    ImmutableMultiset<T> immutableCopy();

}
