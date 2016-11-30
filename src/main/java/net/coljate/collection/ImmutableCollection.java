package net.coljate.collection;

import net.coljate.UnmodifiableIterator;

/**
 *
 * @author ollie
 */
public interface ImmutableCollection<T> extends Collection<T> {

    @Override
    UnmodifiableIterator<T> iterator();

    ImmutableCollection<T> with(T element);

    @Deprecated
    @Override
    default ImmutableCollection<T> immutableCopy() {
        return this;
    }

}
