package net.coljate.set.impl;

import net.coljate.UnmodifiableIterator;
import net.coljate.map.ImmutableMap;
import net.coljate.set.ImmutableMultiset;
import net.coljate.set.Multiset;

/**
 *
 * @author ollie
 */
public class ImmutableHashMultiset<T>
        extends HashMultiset<T>
        implements ImmutableMultiset<T> {

    public static <T> ImmutableMultiset<T> copyOf(final Multiset<T> multiset) {
        return new ImmutableHashMultiset<>(multiset.countElements().immutableCopy());
    }

    protected ImmutableHashMultiset(final ImmutableMap<T, Integer> count) {
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
