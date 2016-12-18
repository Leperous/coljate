package net.coljate.map;

import net.coljate.map.impl.MutableHashCounter;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface Counter<T> extends Set<T> {

    int count(Object element);

    @Override
    default boolean contains(final Object element) {
        return this.count(element) > 0;
    }

    Map<T, Integer> countElements();

    @Override
    MutableCounter<T> mutableCopy();

    @Override
    ImmutableCounter<T> immutableCopy();

    static <T> MutableCounter<T> create() {
        return MutableHashCounter.create();
    }

}
