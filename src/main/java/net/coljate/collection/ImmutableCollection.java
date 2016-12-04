package net.coljate.collection;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.impl.SingletonCollection;
import net.coljate.set.Set;

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

    static <T> SingletonCollection<T> copyOf(final T element) {
        return Set.copyOf(element);
    }

}
