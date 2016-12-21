package net.coljate.counter;

import net.coljate.collection.Collection;
import net.coljate.counter.impl.MutableHashCounter;
import net.coljate.map.Map;

/**
 *
 * @author ollie
 */
public interface Counter<T> extends Collection<T> {

    int count(Object element);

    @Override
    default boolean contains(final Object element) {
        return this.count(element) > 0;
    }

    Map<T, Integer> countElements();

    @Override
    default MutableCounter<T> mutableCopy() {
        return MutableCounter.copyOf(this);
    }

    @Override
    default ImmutableCounter<T> immutableCopy() {
        return ImmutableCounter.copyOf(this);
    }

    static <T> MutableCounter<T> create() {
        return MutableHashCounter.create();
    }

}