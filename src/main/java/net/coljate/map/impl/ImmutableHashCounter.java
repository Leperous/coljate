package net.coljate.map.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.map.ImmutableMap;
import net.coljate.map.Counter;
import net.coljate.map.ImmutableCounter;

/**
 *
 * @author ollie
 */
public class ImmutableHashCounter<T>
        extends HashCounter<T>
        implements ImmutableCounter<T> {

    public static <T> ImmutableCounter<T> copyOf(final Counter<T> multiset) {
        return new ImmutableHashCounter<>(multiset.countElements().immutableCopy());
    }

    protected ImmutableHashCounter(final ImmutableMap<T, Integer> count) {
        super(count);
    }

    @Override
    public ImmutableMap<T, Integer> countElements() {
        return super.countElements().immutableCopy();
    }

    @Override
    public UnmodifiableIterator<T> iterator() {
        return UnmodifiableIterator.wrap(super.iterator());
    }

}
